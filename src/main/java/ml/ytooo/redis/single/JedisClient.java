package ml.ytooo.redis.single;

import java.util.List;
import java.util.Map;
import java.util.Set;

import ml.ytooo.redis.RedisProperties;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.util.SafeEncoder;

public class JedisClient {
    private final int expire = 60000;

    private static JedisPool JEDIS_POOL;

    private static JedisClient JEDIS_CLIENT;

    private JedisClient() {
    }

    public JedisPool getPool() {
        return JEDIS_POOL;
    }

    public Jedis getJedis() {
        return JEDIS_POOL.getResource();
    }

    public static JedisClient getInstance() {

        if (null == JEDIS_CLIENT) {
            synchronized (JedisClient.class) {
                if (null == JEDIS_CLIENT) {
                    JedisPoolConfig config = new JedisPoolConfig();
                    config.setMaxTotal(500);
                    config.setMaxIdle(5);
                    config.setMaxWaitMillis(50000L);
                    config.setTestOnBorrow(true);
                    if (StringUtils.isNotBlank(RedisProperties.password)) {
                        JEDIS_POOL = new JedisPool(config, RedisProperties.IP, RedisProperties.PORT, 10000, RedisProperties.password);
                    } else {
                        JEDIS_POOL = new JedisPool(config, RedisProperties.IP, RedisProperties.PORT);
                    }
                    try {
                        if (null == JEDIS_POOL.getResource()) {
                            throw new JedisException("创建连接池失败");
                        }
                    } catch (JedisException e) {
                        e.printStackTrace();
                        throw new JedisException("创建连接池失败");
                    }
                    JEDIS_CLIENT = new JedisClient();
                }
            }
        }
        return JEDIS_CLIENT;
    }

    public void returnJedis(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }

    }

    public void expire(String key, int seconds) {
        if (seconds > 0) {
            Jedis jedis = this.getJedis();
            jedis.expire(key, seconds);
            jedis.close();
        }
    }

    public void expire(String key) {
        this.expire(key, 60000);
    }
}
