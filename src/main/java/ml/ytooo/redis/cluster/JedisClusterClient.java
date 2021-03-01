package ml.ytooo.redis.cluster;

import redis.clients.jedis.JedisCluster;

public class JedisClusterClient {

    private JedisCluster jedisCluster;

    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    private static JedisClusterClient instance;

    private JedisClusterClient() {
    }

    public static JedisClusterClient getInstance() {
        if (null == instance) {
            synchronized (JedisClusterConfig.class) {
                if (null == instance) {
                    instance = new JedisClusterClient();
                    instance.jedisCluster = JedisClusterConfig.getInstance().getJedisCluster();
                }
            }
        }
        return instance;
    }

}
