package learn.concurrentlearn.reidslearn;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.ZParams;

import java.util.List;
import java.util.UUID;


/**
 * @author Danny.
 * @version 1.0
 * @date 2019/7/8 22:27
 * @description 分布式限流器
 */
public class RateLimiterByRedis {

    /**
     * 限流桶：表示滑动的窗口内的请求的顺序（排名）; 是用sortedSet实现, 分数是一个递增的计数，
     */
    private static final String BUCKET = "BUCKET";
    /**
     * 请求计数桶：标识当前请求顺序，单调递增的计数，每一次请求会给计数+1
     */
    private static final String BUCKET_COUNT = "BUCKET_COUNT";
    /**
     * 使用sortedSet实现
     * 使用当前时间戳为分数，目的用于滑动窗口的边界计算，随时都维护一个滑动窗口
     * 用于保持请求分布均匀，不会出现流量尖峰问题
     * 每次请求，会根据分数清理滑动窗口之外的计数，然后与BUCKET取交集,目的清理BUCKET中的滑动窗口清理，
     * 例如:
     * 已经把滑动窗口之外的计数清掉了
     * BUCKET_MONITOR:[T3:11244443] 交集 BUCKET:[T1:1,T2:2,T3:3]  =>  BUCKET:[T3:3]清理掉了T1:1,T2:2
     */
    private static final String BUCKET_MONITOR = "BUCKET_MONITOR";


    public static String acquire(String key, int limit, long timeOut) {

        JedisPool jedisPool = JedisConf.getInstance();
        Jedis jedis = jedisPool.getResource();
        try {
            //标识当前请求
            String identifier = UUID.randomUUID().toString();
            System.out.println("限流标志：" + identifier);
            //当前时间
            long nowTime = System.currentTimeMillis();
            //开启redis事务
            Transaction transaction = jedis.multi();

            //移除负无穷时间到当前减去超时时间的请求信号，可以将timeout理解为一个滑动窗口
            //其实就相当于保留(nowTime-timeOut)~nowTime内的请求信号
            transaction.zremrangeByScore((BUCKET_MONITOR + key).getBytes(), "-inf".getBytes(),
                    String.valueOf(nowTime - timeOut).getBytes());
            ZParams zParams = new ZParams();
            //交集后的成员的score聚合方式：MAX|MIN|SUM|
            //zParams.aggregate(ZParams.Aggregate.MAX);
            //指定权重因子，1.0对应BUCKET + key，0.0对应BUCKET_MONITOR + key
            //其实就相当于取交集之前：BUCKET + key集合中成员的score*1,BUCKET_MONITOR中的成员score*0
            zParams.weights(1.0, 0.0);
            // 取交集，将(BUCKET + key)的分数从-inf~（now - timeout）的信号数据消除
            // 同时注意到(BUCKET_MONITOR + point)部分权重为0，
            // 即只保留(BUCKET + key)中在[(now - timeout)～now]滑动窗口内的数据
            transaction.zinterstore(BUCKET + key, zParams, BUCKET + key, BUCKET_MONITOR + key);

            //计数器自增
            transaction.incr(BUCKET_COUNT);

            //执行事务
            List<Object> results = transaction.exec();
            //获取最后一个事务操作拿到的自增计数
            long counter = (Long) results.get(results.size() - 1);

            //又开启事务
            transaction = jedis.multi();
            //插入当前计数时间，便于超时清除,目的方便于清理滑动窗口之外的成员
            transaction.zadd(BUCKET_MONITOR + key, nowTime, identifier);
            //添加请求记录，counter可以标识在当前请求滑动窗口内的请求顺序
            transaction.zadd(BUCKET + key, counter, identifier);
            //计算当前请求在计数桶内的排名
            transaction.zrank(BUCKET + key, identifier);
            results = transaction.exec();
            //获取identifier的排名，判断请求是否取得了信号量
            long rank = (Long) results.get(results.size() - 1);
            if (rank < limit) {
                return identifier;
            } else {
                //没有获取到信号量，清理之前放入redis 中垃圾数据
                transaction = jedis.multi();
                transaction.zrem(BUCKET_MONITOR + key, identifier);
                transaction.zrem(BUCKET + key, identifier);
                transaction.exec();
            }
            return null;
        } finally {
            jedis.close();
        }
    }
}
