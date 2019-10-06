import learn.concurrentlearn.reidslearn.JedisConf;
import learn.concurrentlearn.reidslearn.RateLimiterByRedis;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/7/9 10:58
 * @description
 */
public class JedisTest {

    private Jedis jedis = null;

    private static final String KEY = "sorted_set";

    private static final String VALUE = "num-";


    private int getRandomInt() {
        return new Random().nextInt(100);
    }

    @Before
    public void before() {
        JedisPool jedisPool = JedisConf.getInstance();
        this.jedis = jedisPool.getResource();
    }

    @Test
    public void ZAdd() {
        Map<String, Double> sourceMember = new HashMap<String, Double>();
        for (int i = 0; i < 3; i++) {
            double score = getRandomInt();
            sourceMember.put(VALUE + score, score);
        }
        jedis.zadd(KEY, sourceMember);
        ZRANGE();
    }

    /**
     * ZCARD key
     * 返回有序集 key 的基数。
     */
    @Test
    public void ZCARD() {
        System.out.println(jedis.zcard(KEY));
    }

    @Test
    public void ZCOUNT() {
        System.out.println(jedis.zcount(KEY, "-inf", "+inf"));
        System.out.println(jedis.zcount(KEY, 0, 3));
    }

    @Test
    public void ZINCRBY() {
        ZRANGE();
        jedis.zincrby(KEY, 20, VALUE + 10);
        ZRANGE();

    }

    /**
     * ZRANGE key start stop [WITHSCORES]
     * 返回有序集 key 中，指定区间内的成员。
     * 其中成员的位置按 score 值递增(从小到大)来排序。
     * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
     * 如果你需要成员按 score 值递减(从大到小)来排列，请使用 ZREVRANGE 命令。
     * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
     * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
     * 超出范围的下标并不会引起错误。
     * 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。
     * 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。
     * 可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。
     * 客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。
     * <p/>
     * ZREVRANGE key start stop [WITHSCORES]
     * 返回有序集 key 中，指定区间内的成员。
     * 其中成员的位置按 score 值递减(从大到小)来排列。
     * 具有相同 score 值的成员按字典序的逆序(reverse lexicographical order)排列。
     * 除了成员按 score 值递减的次序排列这一点外， ZREVRANGE 命令的其他方面和 ZRANGE 命令一样。
     */
    @Test
    public void ZRANGE() {
        Set<Tuple> tuples = jedis.zrangeWithScores(KEY, 0, -1);
        for (Tuple tuple : tuples) {
            System.out.println(tuple.getElement() + ":" + tuple.getScore());
        }
    }


    /**
     * ZRANGEBYSCORE key min max [WITHSCORES] [LIMIT offset count]
     * 返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。有序集成员按 score 值递增(从小到大)次序排列。
     * 具有相同 score 值的成员按字典序(lexicographical order)来排列(该属性是有序集提供的，不需要额外的计算)。
     * 可选的 LIMIT 参数指定返回结果的数量及区间(就像SQL中的 SELECT LIMIT offset, count )，注意当 offset
     * 很大时，定位 offset 的操作可能需要遍历整个有序集，此过程最坏复杂度为 O(N) 时间。
     * 可选的 WITHSCORES 参数决定结果集是单单返回有序集的成员，还是将有序集成员及其 score 值一起返回。
     * 该选项自 Redis 2.0 版本起可用。
     * 区间及无限
     * min 和 max 可以是 -inf 和 +inf ，这样一来，你就可以在不知道有序集的最低和最高 score 值的情况下，使用 ZRANGEBYSCORE 这类命令。
     * 默认情况下，区间的取值使用闭区间 (小于等于或大于等于)，你也可以通过给参数前增加 ( 符号来使用可选的开区间 (小于或大于)。
     * <p/>
     * ZREVRANGEBYSCORE key max min [WITHSCORES] [LIMIT offset count]
     * 返回有序集 key 中， score 值介于 max 和 min 之间(默认包括等于 max 或 min )的所有的成员。
     * 有序集成员按 score 值递减(从大到小)的次序排列。
     * 具有相同 score 值的成员按字典序的逆序(reverse lexicographical order )排列。
     * 除了成员按 score 值递减的次序排列这一点外， ZREVRANGEBYSCORE 命令的其他方面和 ZRANGEBYSCORE 命令一样。
     */
    @Test
    public void ZRANGEBYSCORE() {
        System.out.println(jedis.zrangeByScore(KEY, 0, 100));
        System.out.println(jedis.zrangeByScore(KEY, 0, 100, 8, 15));
        System.out.println(jedis.zrangeByScore(KEY, "-inf", "+inf"));
        System.out.println(jedis.zrangeByScore(KEY, "-inf", "+inf", 8, 1));
        Set<Tuple> tuples = jedis.zrangeByScoreWithScores(KEY, "-inf", "+inf", 8, 1);
        for (Tuple tuple : tuples) {
            System.out.println(tuple.getElement() + "=" + tuple.getScore());
        }
    }

    /**
     * ZRANK key member
     * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递增(从小到大)顺序排列。
     * 排名以 0 为底，也就是说， score 值最小的成员排名为 0 。
     * 使用 ZREVRANK 命令可以获得成员按 score 值递减(从大到小)排列的排名。
     * <p/>
     * ZREVRANK key member
     * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递减(从大到小)排序。
     * 排名以 0 为底，也就是说， score 值最大的成员排名为 0 。
     * 使用 ZRANK 命令可以获得成员按 score 值递增(从小到大)排列的排名。
     */
    @Test
    public void ZRANK() {
        System.out.println(jedis.zrank(KEY, "xiaohj35.0"));
    }

    /**
     * ZREM key member [member ...]
     * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。
     * 当 key 存在但不是有序集类型时，返回一个错误。
     */
    @Test
    public void ZREM() {
        ZRANGE();
        jedis.zrem(KEY, "xiaohj14.0", "xiaohj21.0", "xiaohj25.0", "xiaohj35.0", "xiaohj96.0");
        System.out.println("######################");
        ZRANGE();
    }

    /**
     * ZREMRANGEBYRANK key start stop
     * 移除有序集 key 中，指定排名(rank)区间内的所有成员。
     * 区间分别以下标参数 start 和 stop 指出，包含 start 和 stop 在内。
     * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
     * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
     */
    @Test
    public void ZREMRANGEBYRANK() {
        ZRANGE();
        jedis.zremrangeByRank(KEY, 0, 1);
        System.out.println("##################");
        ZRANGE();
    }


    /**
     * ZREMRANGEBYSCORE key min max
     * 移除有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。
     * 自版本2.1.6开始， score 值等于 min 或 max 的成员也可以不包括在内，详情请参见 ZRANGEBYSCORE 命令。
     */
    @Test
    public void ZREMRANGEBYSCORE() {
        ZRANGE();
        //删除53<score<63
        jedis.zremrangeByScore(KEY, "53", "63");
        System.out.println("#####################");
        ZRANGE();
    }

    /**
     * ZSCORE key member
     * 返回有序集 key 中，成员 member 的 score 值。
     * 如果 member 元素不是有序集 key 的成员，或 key 不存在，返回 nil 。
     */
    @Test
    public void ZSCORE() {
        System.out.println(jedis.zscore(KEY, "xiaohj14.0"));
    }

    /**
     * ZUNIONSTORE destination numkeys key [key ...] [WEIGHTS weight [weight ...]] [AGGREGATE SUM|MIN|MAX]
     * 计算给定的一个或多个有序集的并集，其中给定 key 的数量必须以 numkeys 参数指定，并将该并集(结果集)储存到 destination 。
     * 默认情况下，结果集中某个成员的 score 值是所有给定集下该成员 score 值之 和 。
     * WEIGHTS
     * 使用 WEIGHTS 选项，你可以为 每个 给定有序集 分别 指定一个乘法因子(multiplication factor)，
     * 每个给定有序集的所有成员的 score 值在传递给聚合函数(aggregation function)之前都要先乘以该有序集的因子。
     * 如果没有指定 WEIGHTS 选项，乘法因子默认设置为 1 。
     * AGGREGATE
     * 使用 AGGREGATE 选项，你可以指定并集的结果集的聚合方式。
     * 默认使用的参数 SUM ，可以将所有集合中某个成员的score值之和作为结果集中该成员的 score 值；
     * 使用参数 MIN ，可以将所有集合中某个成员的最小score值作为结果集中该成员的score值；
     * 而参数MAX则是将所有集合中某个成员的最大score值作为结果集中该成员的score值。
     */
    @Test
    public void ZUNIONSTORE() {
        ZADDForKey(KEY + 0);
        ZRANGE();
        System.out.println("###############################");
        System.out.println(jedis.zrangeWithScores(KEY + 0, 0, -1));
        ZParams zParams = new ZParams();
        zParams.aggregate(ZParams.Aggregate.SUM);
//        zParams.weightsByDouble(1);
        jedis.zunionstore(KEY, zParams, KEY, KEY + 0);
        System.out.println("###############################");
        System.out.println(jedis.zrangeWithScores(KEY, 0, -1));
    }

    /**
     * ZINTERSTORE destination numkeys key [key ...] [WEIGHTS weight [weight ...]] [AGGREGATE SUM|MIN|MAX]
     * 计算给定的一个或多个有序集的交集，其中给定 key 的数量必须以 numkeys 参数指定，并将该交集(结果集)储存到 destination 。
     * 默认情况下，结果集中某个成员的 score 值是所有给定集下该成员 score 值之和.
     * 关于 WEIGHTS 和 AGGREGATE 选项的描述，参见 ZUNIONSTORE 命令。
     */
    @Test
    public void ZINTERSTORE() {
        System.out.println(jedis.zrangeWithScores("BUCKET_MONITOR", 0, -1));
        System.out.println("###############################");
        System.out.println(jedis.zrangeWithScores("BUCKETtest001", 0, -1));
        System.out.println("###############################");
        ZParams zParams = new ZParams();
        //zParams.aggregate(ZParams.Aggregate.SUM);
//        zParams.weightsByDouble(1d);
        zParams.weights(1.0, 0.0);
        System.out.println("#############求交集开始##################");
        jedis.zinterstore("BUCKETtest001", zParams, "BUCKETtest001", "BUCKET_MONITOR");
        System.out.println("###############交集结束################");
        System.out.println(jedis.zrangeWithScores("BUCKETtest001", 0, -1));
    }


    /**
     * ZRANGEBYLEX key min max [LIMIT offset count]
     * 当有序集合的所有成员都具有相同的分值时， 有序集合的元素会根据成员的字典序（lexicographical ordering）来进行排序，
     * 而这个命令则可以返回给定的有序集合键 key 中， 值介于 min 和 max 之间的成员。
     * 如果有序集合里面的成员带有不同的分值， 那么命令返回的结果是未指定的（unspecified）。
     * 命令会使用 C 语言的 memcmp() 函数， 对集合中的每个成员进行逐个字节的对比（byte-by-byte compare）， 并按照从低到高的顺序， 返回排序后的集合成员。
     * 如果两个字符串有一部分内容是相同的话， 那么命令会认为较长的字符串比较短的字符串要大。
     * 可选的 LIMIT offset count 参数用于获取指定范围内的匹配元素 （就像 SQL 中的 SELECT LIMIT offset count 语句）。 需要注意的一点是，
     * 如果 offset 参数的值非常大的话， 那么命令在返回结果之前， 需要先遍历至 offset 所指定的位置， 这个操作会为命令加上最多 O(N) 复杂度。
     * 如何指定范围区间
     * 合法的 min 和 max 参数必须包含 ( 或者 [ ， 其中 ( 表示开区间（指定的值不会被包含在范围之内）， 而 [ 则表示闭区间（指定的值会被包含在范围之内）。
     * 特殊值 + 和 - 在 min 参数以及 max 参数中具有特殊的意义， 其中 + 表示正无限， 而 - 表示负无限。 因此， 向一个所有成员的分值都相同的有序集合发送命令
     * ZRANGEBYLEX <zset> - + ， 命令将返回有序集合中的所有元素。
     */
    @Test
    public void ZRANGEBYLEX() {
        /**
         *
         redis> ZADD myzset 0 a 0 b 0 c 0 d 0 e 0 f 0 g
         (integer) 7
         redis> ZRANGEBYLEX myzset - [c
         1) "a"
         2) "b"
         3) "c"

         redis> ZRANGEBYLEX myzset - (c
         1) "a"
         2) "b"

         redis> ZRANGEBYLEX myzset [aaa (g
         1) "b"
         2) "c"
         3) "d"
         4) "e"
         5) "f"
         */
        ZRANGE();
        System.out.println(jedis.zrangeByLex(KEY, "-", "+"));
        System.out.println(jedis.zrangeByLex(KEY, "-", "+", 1, 2));
    }


    private void ZADDForKey(String key) {
        if (StringUtils.isEmpty(key)) {
            key = KEY;
        }
        Map<String, Double> sourceMember = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            double score = getRandomInt();
            sourceMember.put(VALUE + score, score);
        }
        jedis.zadd(key, sourceMember);
    }

    @Test
    public void testRateRedis() {
        String key = "test001";
        long timeOut = 1000L;
        int limit = 5;
        String token;
        for (int i = 0; i < 6; i++) {
            token = RateLimiterByRedis.acquire(key, limit, timeOut);
            if (token == null) {
                System.out.println("请求太频繁");
            } else {
                System.out.println("请求成功");
            }
        }

    }

}
