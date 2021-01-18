package ml.ytooo.redis.cluster;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.JedisCluster;

import java.lang.reflect.Type;
import java.util.List;

public class JedisClusterClient {

    private static final JedisCluster jedisCluster = JedisClusterConfig.getInstance().getJedisCluster();


    private static JedisClusterClient instance;

    private JedisClusterClient() {
    }

    public static JedisClusterClient getInstance() {
        if (null == instance) {
            synchronized (JedisClusterConfig.class) {
                if (null == instance) {
                    instance = new JedisClusterClient();
                }
            }
        }
        return instance;
    }

    /**
     * get
     *
     * @param key
     * @param type
     * @param <T>
     * @return
     */
    public <T> T get(String key, Type type) {
        String json = jedisCluster.get(key);
        if (StringUtils.isNotEmpty(json)) {
            return JSON.parseObject(json, type);
        } else {
            return null;
        }
    }

    /**
     * get
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(String key, Class<T> clazz) {
        String json = jedisCluster.get(key);
        if (StringUtils.isNotEmpty(json)) {
            return JSON.parseObject(json, clazz);
        } else {
            return null;
        }
    }

    /**
     * set
     *
     * @param key
     * @param o
     */
    public void set(String key, Object o) {
        String json = JSON.toJSONString(o);
        jedisCluster.set(key, json);
    }

    /**
     * setAndExpire
     *
     * @param key
     * @param o
     * @param expire
     */
    public void setAndExpire(String key, Object o, int expire) {
        String json = JSON.toJSONString(o);
        jedisCluster.set(key, json);
        jedisCluster.expire(key, expire);
    }

//    public long del(String key) {
//        return jedisCluster.del(key);
//    }

    /**
     * get
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return jedisCluster.get(key);
    }

//    public String get(String key, int select) {
//        jedisCluster.select(select);
//        return jedisCluster.get(key);
//    }

    /**
     * set
     *
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        return jedisCluster.set(key, value);
    }

    /**
     * hget
     *
     * @param hkey
     * @param key
     * @return
     */
    public String hget(String hkey, String key) {
        return jedisCluster.hget(hkey, key);
    }

    /**
     * hset
     *
     * @param hkey
     * @param key
     * @param value
     * @return
     */
    public long hset(String hkey, String key, String value) {
        return jedisCluster.hset(hkey, key, value);
    }

    /**
     * incr
     *
     * @param key
     * @return
     */
    public long incr(String key) {
        return jedisCluster.incr(key);
    }

    /**
     * decr
     *
     * @param key
     * @return
     */
    public Long decr(String key) {
        return jedisCluster.decr(key);
    }

    /**
     * expire
     *
     * @param key
     * @param second
     * @return
     */
    public long expire(String key, int second) {
        return jedisCluster.expire(key, second);
    }

    /**
     * ttl
     *
     * @param key
     * @return
     */
    public long ttl(String key) {
        return jedisCluster.ttl(key);
    }

    /**
     * del
     *
     * @param key
     * @return
     */
    public long del(String key) {
        return jedisCluster.del(key);
    }

    /**
     * hdel
     *
     * @param hkey
     * @param key
     * @return
     */
    public long hdel(String hkey, String key) {

        return jedisCluster.hdel(hkey, key);
    }

    /**
     * rpush
     *
     * @param key
     * @param string
     * @return
     */
    public Long rpush(String key, String string) {
        return jedisCluster.rpush(key, string);
    }

    /**
     * lpush
     *
     * @param key
     * @param string
     * @return
     */
    public Long lpush(String key, String string) {
        return jedisCluster.lpush(key, string);
    }

    /**
     * exists
     *
     * @param key
     * @return
     */
    public Boolean exists(String key) {
        return jedisCluster.exists(key);
    }

    /**
     * brpop
     *
     * @param timeout
     * @param key
     * @return
     */
    public List<String> brpop(int timeout, String key) {
        return jedisCluster.brpop(timeout, key);
    }

    /**
     * hexists
     */
    public Boolean hexists(String hkey, String key) {
        return jedisCluster.hexists(hkey, key);
    }

}
