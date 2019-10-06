package learn.concurrentlearn.reidslearn;


import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/7/8 22:04
 * @description
 */
public class JedisConf {
    private JedisConf() {
    }


    public static class HandleInstance {
        private static JedisPool gencJedis() {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(8);
            config.setMaxTotal(18);
            JedisPool pool = new JedisPool(config, "39.96.25.83", 6379,
                    2000, "xiaohj@1990");
            return pool;
        }

    }

    public static JedisPool getInstance() {
        return HandleInstance.gencJedis();
    }


}
