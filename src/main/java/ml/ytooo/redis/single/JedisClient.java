package ml.ytooo.redis.single;

import java.util.List;
import java.util.Map;
import java.util.Set;

import ml.ytooo.redis.RedisProperties;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.util.SafeEncoder;

public class JedisClient {
    private final int expire = 60000;

    public JedisClient.Keys KEYS;

    public JedisClient.Strings STRINGS;

    public JedisClient.Lists LISTS;

    public JedisClient.Sets SETS;

    public JedisClient.Hash HASH;

    public JedisClient.SortSet SORTSET;

    private static JedisPool jedisPool = null;

    private static final JedisClient JEDIS_CLIENT;

    private JedisClient() {
    }

    public JedisPool getPool() {
        return jedisPool;
    }

    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    public static JedisClient getInstance() {
        return JEDIS_CLIENT;
    }

    public void returnJedis(Jedis jedis) {
        if (null != jedis && null != jedisPool) {
            jedisPool.returnResource(jedis);
        }

    }

    public static void returnBrokenResource(Jedis jedis) {
        if (null != jedis && null != jedisPool) {
            jedisPool.returnResource(jedis);
        }

    }

    public void expire(String key, int seconds) {
        if (seconds > 0) {
            Jedis jedis = this.getJedis();
            jedis.expire(key, seconds);
            this.returnJedis(jedis);
        }
    }

    public void expire(String key) {
        this.expire(key, 60000);
    }

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(500);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(100000L);
        config.setTestOnBorrow(true);
        if (RedisProperties.password != null && !"".equals(RedisProperties.password)) {
            jedisPool = new JedisPool(config, RedisProperties.IP, RedisProperties.PORT, 10000, RedisProperties.password);
        } else {
            jedisPool = new JedisPool(config, RedisProperties.IP, RedisProperties.PORT);
        }

        JEDIS_CLIENT = new JedisClient();
    }

    public class Lists {
        public Lists() {
        }

        public long llen(String key) {
            return this.llen(SafeEncoder.encode(key));
        }

        public long llen(byte[] key) {
            Jedis sjedis = JedisClient.this.getJedis();
            long count = sjedis.llen(key);
            JedisClient.this.returnJedis(sjedis);
            return count;
        }

        public String lset(byte[] key, int index, byte[] value) {
            Jedis jedis = JedisClient.this.getJedis();
            String status = jedis.lset(key, (long) index, value);
            JedisClient.this.returnJedis(jedis);
            return status;
        }

        public String lset(String key, int index, String value) {
            return this.lset(SafeEncoder.encode(key), index, SafeEncoder.encode(value));
        }

        public long linsert(String key, LIST_POSITION where, String pivot, String value) {
            return this.linsert(SafeEncoder.encode(key), where, SafeEncoder.encode(pivot), SafeEncoder.encode(value));
        }

        public long linsert(byte[] key, LIST_POSITION where, byte[] pivot, byte[] value) {
            Jedis jedis = JedisClient.this.getJedis();
            long count = jedis.linsert(key, where, pivot, value);
            JedisClient.this.returnJedis(jedis);
            return count;
        }

        public String lindex(String key, int index) {
            return SafeEncoder.encode(this.lindex(SafeEncoder.encode(key), index));
        }

        public byte[] lindex(byte[] key, int index) {
            Jedis sjedis = JedisClient.this.getJedis();
            byte[] value = sjedis.lindex(key, (long) index);
            JedisClient.this.returnJedis(sjedis);
            return value;
        }

        public String lpop(String key) {
            return SafeEncoder.encode(this.lpop(SafeEncoder.encode(key)));
        }

        public byte[] lpop(byte[] key) {
            Jedis jedis = JedisClient.this.getJedis();
            byte[] value = jedis.lpop(key);
            JedisClient.this.returnJedis(jedis);
            return value;
        }

        public String rpop(String key) {
            Jedis jedis = JedisClient.this.getJedis();
            String value = jedis.rpop(key);
            JedisClient.this.returnJedis(jedis);
            return value;
        }

        public long lpush(String key, String value) {
            return this.lpush(SafeEncoder.encode(key), SafeEncoder.encode(value));
        }

        public long rpush(String key, String value) {
            Jedis jedis = JedisClient.this.getJedis();
            long count = jedis.rpush(key, new String[] { value });
            JedisClient.this.returnJedis(jedis);
            return count;
        }

        public long rpush(byte[] key, byte[] value) {
            Jedis jedis = JedisClient.this.getJedis();
            long count = jedis.rpush(key, new byte[][] { value });
            JedisClient.this.returnJedis(jedis);
            return count;
        }

        public long lpush(byte[] key, byte[] value) {
            Jedis jedis = JedisClient.this.getJedis();
            long count = jedis.lpush(key, new byte[][] { value });
            JedisClient.this.returnJedis(jedis);
            return count;
        }

        public List<String> lrange(String key, long start, long end) {
            Jedis sjedis = JedisClient.this.getJedis();
            List<String> list = sjedis.lrange(key, start, end);
            JedisClient.this.returnJedis(sjedis);
            return list;
        }

        public List<byte[]> lrange(byte[] key, int start, int end) {
            Jedis sjedis = JedisClient.this.getJedis();
            List<byte[]> list = sjedis.lrange(key, (long) start, (long) end);
            JedisClient.this.returnJedis(sjedis);
            return list;
        }

        public long lrem(byte[] key, int c, byte[] value) {
            Jedis jedis = JedisClient.this.getJedis();
            long count = jedis.lrem(key, (long) c, value);
            JedisClient.this.returnJedis(jedis);
            return count;
        }

        public long lrem(String key, int c, String value) {
            return this.lrem(SafeEncoder.encode(key), c, SafeEncoder.encode(value));
        }

        public String ltrim(byte[] key, int start, int end) {
            Jedis jedis = JedisClient.this.getJedis();
            String str = jedis.ltrim(key, (long) start, (long) end);
            JedisClient.this.returnJedis(jedis);
            return str;
        }

        public String ltrim(String key, int start, int end) {
            return this.ltrim(SafeEncoder.encode(key), start, end);
        }
    }

    public class Strings {
        public Strings() {
        }

        public String get(String key) {
            Jedis sjedis = JedisClient.this.getJedis();
            String value = sjedis.get(key);
            JedisClient.this.returnJedis(sjedis);
            return value;
        }

        public byte[] get(byte[] key) {
            Jedis sjedis = JedisClient.this.getJedis();
            byte[] value = sjedis.get(key);
            JedisClient.this.returnJedis(sjedis);
            return value;
        }

        public String setEx(String key, int seconds, String value) {
            Jedis jedis = JedisClient.this.getJedis();
            String str = jedis.setex(key, seconds, value);
            JedisClient.this.returnJedis(jedis);
            return str;
        }

        public String setEx(byte[] key, int seconds, byte[] value) {
            Jedis jedis = JedisClient.this.getJedis();
            String str = jedis.setex(key, seconds, value);
            JedisClient.this.returnJedis(jedis);
            return str;
        }

        public long setnx(String key, String value) {
            Jedis jedis = JedisClient.this.getJedis();
            long str = jedis.setnx(key, value);
            JedisClient.this.returnJedis(jedis);
            return str;
        }

        public String set(String key, String value) {
            return this.set(SafeEncoder.encode(key), SafeEncoder.encode(value));
        }

        public String set(String key, byte[] value) {
            return this.set(SafeEncoder.encode(key), value);
        }

        public String set(byte[] key, byte[] value) {
            Jedis jedis = JedisClient.this.getJedis();
            String status = jedis.set(key, value);
            JedisClient.this.returnJedis(jedis);
            return status;
        }

        public long setRange(String key, long offset, String value) {
            Jedis jedis = JedisClient.this.getJedis();
            long len = jedis.setrange(key, offset, value);
            JedisClient.this.returnJedis(jedis);
            return len;
        }

        public long append(String key, String value) {
            Jedis jedis = JedisClient.this.getJedis();
            long len = jedis.append(key, value);
            JedisClient.this.returnJedis(jedis);
            return len;
        }

        public long decrBy(String key, long number) {
            Jedis jedis = JedisClient.this.getJedis();
            long len = jedis.decrBy(key, number);
            JedisClient.this.returnJedis(jedis);
            return len;
        }

        public long incrBy(String key, long number) {
            Jedis jedis = JedisClient.this.getJedis();
            long len = jedis.incrBy(key, number);
            JedisClient.this.returnJedis(jedis);
            return len;
        }

        public String getrange(String key, long startOffset, long endOffset) {
            Jedis sjedis = JedisClient.this.getJedis();
            String value = sjedis.getrange(key, startOffset, endOffset);
            JedisClient.this.returnJedis(sjedis);
            return value;
        }

        public String getSet(String key, String value) {
            Jedis jedis = JedisClient.this.getJedis();
            String str = jedis.getSet(key, value);
            JedisClient.this.returnJedis(jedis);
            return str;
        }

        public List<String> mget(String... keys) {
            Jedis jedis = JedisClient.this.getJedis();
            List<String> str = jedis.mget(keys);
            JedisClient.this.returnJedis(jedis);
            return str;
        }

        public String mset(String... keysvalues) {
            Jedis jedis = JedisClient.this.getJedis();
            String str = jedis.mset(keysvalues);
            JedisClient.this.returnJedis(jedis);
            return str;
        }

        public long strlen(String key) {
            Jedis jedis = JedisClient.this.getJedis();
            long len = jedis.strlen(key);
            JedisClient.this.returnJedis(jedis);
            return len;
        }
    }

    public class Hash {
        public Hash() {
        }

        public long hdel(String key, String fieid) {
            Jedis jedis = JedisClient.this.getJedis();
            long s = jedis.hdel(key, new String[] { fieid });
            JedisClient.this.returnJedis(jedis);
            return s;
        }

        public long hdel(String key) {
            Jedis jedis = JedisClient.this.getJedis();
            long s = jedis.del(key);
            JedisClient.this.returnJedis(jedis);
            return s;
        }

        public boolean hexists(String key, String fieid) {
            Jedis sjedis = JedisClient.this.getJedis();
            boolean s = sjedis.hexists(key, fieid);
            JedisClient.this.returnJedis(sjedis);
            return s;
        }

        public String hget(String key, String fieid) {
            Jedis sjedis = JedisClient.this.getJedis();
            String s = sjedis.hget(key, fieid);
            JedisClient.this.returnJedis(sjedis);
            return s;
        }

        public byte[] hget(byte[] key, byte[] fieid) {
            Jedis sjedis = JedisClient.this.getJedis();
            byte[] s = sjedis.hget(key, fieid);
            JedisClient.this.returnJedis(sjedis);
            return s;
        }

        public Map<String, String> hgetAll(String key) {
            Jedis sjedis = JedisClient.this.getJedis();
            Map<String, String> map = sjedis.hgetAll(key);
            JedisClient.this.returnJedis(sjedis);
            return map;
        }

        public long hset(String key, String fieid, String value) {
            Jedis jedis = JedisClient.this.getJedis();
            long s = jedis.hset(key, fieid, value);
            JedisClient.this.returnJedis(jedis);
            return s;
        }

        public long hset(String key, String fieid, byte[] value) {
            Jedis jedis = JedisClient.this.getJedis();
            long s = jedis.hset(key.getBytes(), fieid.getBytes(), value);
            JedisClient.this.returnJedis(jedis);
            return s;
        }

        public long hsetnx(String key, String fieid, String value) {
            Jedis jedis = JedisClient.this.getJedis();
            long s = jedis.hsetnx(key, fieid, value);
            JedisClient.this.returnJedis(jedis);
            return s;
        }

        public List<String> hvals(String key) {
            Jedis sjedis = JedisClient.this.getJedis();
            List<String> list = sjedis.hvals(key);
            JedisClient.this.returnJedis(sjedis);
            return list;
        }

        public long hincrby(String key, String fieid, long value) {
            Jedis jedis = JedisClient.this.getJedis();
            long s = jedis.hincrBy(key, fieid, value);
            JedisClient.this.returnJedis(jedis);
            return s;
        }

        public Set<String> hkeys(String key) {
            Jedis sjedis = JedisClient.this.getJedis();
            Set<String> set = sjedis.hkeys(key);
            JedisClient.this.returnJedis(sjedis);
            return set;
        }

        public long hlen(String key) {
            Jedis sjedis = JedisClient.this.getJedis();
            long len = sjedis.hlen(key);
            JedisClient.this.returnJedis(sjedis);
            return len;
        }

        public List<String> hmget(String key, String... fieids) {
            Jedis sjedis = JedisClient.this.getJedis();
            List<String> list = sjedis.hmget(key, fieids);
            JedisClient.this.returnJedis(sjedis);
            return list;
        }

        public List<byte[]> hmget(byte[] key, byte[]... fieids) {
            Jedis sjedis = JedisClient.this.getJedis();
            List<byte[]> list = sjedis.hmget(key, fieids);
            JedisClient.this.returnJedis(sjedis);
            return list;
        }

        public String hmset(String key, Map<String, String> map) {
            Jedis jedis = JedisClient.this.getJedis();
            String s = jedis.hmset(key, map);
            JedisClient.this.returnJedis(jedis);
            return s;
        }

        public String hmset(byte[] key, Map<byte[], byte[]> map) {
            Jedis jedis = JedisClient.this.getJedis();
            String s = jedis.hmset(key, map);
            JedisClient.this.returnJedis(jedis);
            return s;
        }
    }

    public class SortSet {
        public SortSet() {
        }

        public long zadd(String key, double score, String member) {
            Jedis jedis = JedisClient.this.getJedis();
            long s = jedis.zadd(key, score, member);
            JedisClient.this.returnJedis(jedis);
            return s;
        }

        public long zcard(String key) {
            Jedis sjedis = JedisClient.this.getJedis();
            long len = sjedis.zcard(key);
            JedisClient.this.returnJedis(sjedis);
            return len;
        }

        public long zcount(String key, double min, double max) {
            Jedis sjedis = JedisClient.this.getJedis();
            long len = sjedis.zcount(key, min, max);
            JedisClient.this.returnJedis(sjedis);
            return len;
        }

        public long zlength(String key) {
            long len = 0L;
            Set<String> set = this.zrange(key, 0, -1);
            len = (long) set.size();
            return len;
        }

        public double zincrby(String key, double score, String member) {
            Jedis jedis = JedisClient.this.getJedis();
            double s = jedis.zincrby(key, score, member);
            JedisClient.this.returnJedis(jedis);
            return s;
        }

        public Set<String> zrange(String key, int start, int end) {
            Jedis sjedis = JedisClient.this.getJedis();
            Set<String> set = sjedis.zrange(key, (long) start, (long) end);
            JedisClient.this.returnJedis(sjedis);
            return set;
        }

        public Set<String> zrangeByScore(String key, double min, double max) {
            Jedis sjedis = JedisClient.this.getJedis();
            Set<String> set = sjedis.zrangeByScore(key, min, max);
            JedisClient.this.returnJedis(sjedis);
            return set;
        }

        public long zrank(String key, String member) {
            Jedis sjedis = JedisClient.this.getJedis();
            long index = sjedis.zrank(key, member);
            JedisClient.this.returnJedis(sjedis);
            return index;
        }

        public long zrevrank(String key, String member) {
            Jedis sjedis = JedisClient.this.getJedis();
            long index = sjedis.zrevrank(key, member);
            JedisClient.this.returnJedis(sjedis);
            return index;
        }

        public long zrem(String key, String member) {
            Jedis jedis = JedisClient.this.getJedis();
            long s = jedis.zrem(key, new String[] { member });
            JedisClient.this.returnJedis(jedis);
            return s;
        }

        public long zrem(String key) {
            Jedis jedis = JedisClient.this.getJedis();
            long s = jedis.del(key);
            JedisClient.this.returnJedis(jedis);
            return s;
        }

        public long zremrangeByRank(String key, int start, int end) {
            Jedis jedis = JedisClient.this.getJedis();
            long s = jedis.zremrangeByRank(key, (long) start, (long) end);
            JedisClient.this.returnJedis(jedis);
            return s;
        }

        public long zremrangeByScore(String key, double min, double max) {
            Jedis jedis = JedisClient.this.getJedis();
            long s = jedis.zremrangeByScore(key, min, max);
            JedisClient.this.returnJedis(jedis);
            return s;
        }

        public Set<String> zrevrange(String key, int start, int end) {
            Jedis sjedis = JedisClient.this.getJedis();
            Set<String> set = sjedis.zrevrange(key, (long) start, (long) end);
            JedisClient.this.returnJedis(sjedis);
            return set;
        }

        public double zscore(String key, String memebr) {
            Jedis sjedis = JedisClient.this.getJedis();
            Double score = sjedis.zscore(key, memebr);
            JedisClient.this.returnJedis(sjedis);
            return score != null ? score : 0.0D;
        }
    }

    public class Sets {
        public Sets() {
        }

        public long sadd(String key, String member) {
            Jedis jedis = JedisClient.this.getJedis();
            long s = jedis.sadd(key, new String[] { member });
            JedisClient.this.returnJedis(jedis);
            return s;
        }

        public long sadd(byte[] key, byte[] member) {
            Jedis jedis = JedisClient.this.getJedis();
            long s = jedis.sadd(key, new byte[][] { member });
            JedisClient.this.returnJedis(jedis);
            return s;
        }

        public long scard(String key) {
            Jedis sjedis = JedisClient.this.getJedis();
            long len = sjedis.scard(key);
            JedisClient.this.returnJedis(sjedis);
            return len;
        }

        public Set<String> sdiff(String... keys) {
            Jedis jedis = JedisClient.this.getJedis();
            Set<String> set = jedis.sdiff(keys);
            JedisClient.this.returnJedis(jedis);
            return set;
        }

        public long sdiffstore(String newkey, String... keys) {
            Jedis jedis = JedisClient.this.getJedis();
            long s = jedis.sdiffstore(newkey, keys);
            JedisClient.this.returnJedis(jedis);
            return s;
        }

        public Set<String> sinter(String... keys) {
            Jedis jedis = JedisClient.this.getJedis();
            Set<String> set = jedis.sinter(keys);
            JedisClient.this.returnJedis(jedis);
            return set;
        }

        public long sinterstore(String newkey, String... keys) {
            Jedis jedis = JedisClient.this.getJedis();
            long s = jedis.sinterstore(newkey, keys);
            JedisClient.this.returnJedis(jedis);
            return s;
        }

        public boolean sismember(String key, String member) {
            Jedis sjedis = JedisClient.this.getJedis();
            boolean s = sjedis.sismember(key, member);
            JedisClient.this.returnJedis(sjedis);
            return s;
        }

        public Set<String> smembers(String key) {
            Jedis sjedis = JedisClient.this.getJedis();
            Set<String> set = sjedis.smembers(key);
            JedisClient.this.returnJedis(sjedis);
            return set;
        }

        public Set<byte[]> smembers(byte[] key) {
            Jedis sjedis = JedisClient.this.getJedis();
            Set<byte[]> set = sjedis.smembers(key);
            JedisClient.this.returnJedis(sjedis);
            return set;
        }

        public long smove(String srckey, String dstkey, String member) {
            Jedis jedis = JedisClient.this.getJedis();
            long s = jedis.smove(srckey, dstkey, member);
            JedisClient.this.returnJedis(jedis);
            return s;
        }

        public String spop(String key) {
            Jedis jedis = JedisClient.this.getJedis();
            String s = jedis.spop(key);
            JedisClient.this.returnJedis(jedis);
            return s;
        }

        public long srem(String key, String member) {
            Jedis jedis = JedisClient.this.getJedis();
            long s = jedis.srem(key, new String[] { member });
            JedisClient.this.returnJedis(jedis);
            return s;
        }

        public Set<String> sunion(String... keys) {
            Jedis jedis = JedisClient.this.getJedis();
            Set<String> set = jedis.sunion(keys);
            JedisClient.this.returnJedis(jedis);
            return set;
        }

        public long sunionstore(String newkey, String... keys) {
            Jedis jedis = JedisClient.this.getJedis();
            long s = jedis.sunionstore(newkey, keys);
            JedisClient.this.returnJedis(jedis);
            return s;
        }
    }

    public class Keys {
        public Keys() {
        }

        public String flushAll() {
            Jedis jedis = JedisClient.this.getJedis();
            String stata = jedis.flushAll();
            JedisClient.this.returnJedis(jedis);
            return stata;
        }

        public String rename(String oldkey, String newkey) {
            return this.rename(SafeEncoder.encode(oldkey), SafeEncoder.encode(newkey));
        }

        public long renamenx(String oldkey, String newkey) {
            Jedis jedis = JedisClient.this.getJedis();
            long status = jedis.renamenx(oldkey, newkey);
            JedisClient.this.returnJedis(jedis);
            return status;
        }

        public String rename(byte[] oldkey, byte[] newkey) {
            Jedis jedis = JedisClient.this.getJedis();
            String status = jedis.rename(oldkey, newkey);
            JedisClient.this.returnJedis(jedis);
            return status;
        }

        public long expired(String key, int seconds) {
            Jedis jedis = JedisClient.this.getJedis();
            long count = jedis.expire(key, seconds);
            JedisClient.this.returnJedis(jedis);
            return count;
        }

        public long expireAt(String key, long timestamp) {
            Jedis jedis = JedisClient.this.getJedis();
            long count = jedis.expireAt(key, timestamp);
            JedisClient.this.returnJedis(jedis);
            return count;
        }

        public long ttl(String key) {
            Jedis sjedis = JedisClient.this.getJedis();
            long len = sjedis.ttl(key);
            JedisClient.this.returnJedis(sjedis);
            return len;
        }

        public long persist(String key) {
            Jedis jedis = JedisClient.this.getJedis();
            long count = jedis.persist(key);
            JedisClient.this.returnJedis(jedis);
            return count;
        }

        public long del(String... keys) {
            Jedis jedis = JedisClient.this.getJedis();
            long count = jedis.del(keys);
            JedisClient.this.returnJedis(jedis);
            return count;
        }

        public long del(byte[]... keys) {
            Jedis jedis = JedisClient.this.getJedis();
            long count = jedis.del(keys);
            JedisClient.this.returnJedis(jedis);
            return count;
        }

        public boolean exists(String key) {
            Jedis sjedis = JedisClient.this.getJedis();
            boolean exis = sjedis.exists(key);
            JedisClient.this.returnJedis(sjedis);
            return exis;
        }

        public List<String> sort(String key) {
            Jedis sjedis = JedisClient.this.getJedis();
            List<String> list = sjedis.sort(key);
            JedisClient.this.returnJedis(sjedis);
            return list;
        }

        public List<String> sort(String key, SortingParams parame) {
            Jedis sjedis = JedisClient.this.getJedis();
            List<String> list = sjedis.sort(key, parame);
            JedisClient.this.returnJedis(sjedis);
            return list;
        }

        public String type(String key) {
            Jedis sjedis = JedisClient.this.getJedis();
            String type = sjedis.type(key);
            JedisClient.this.returnJedis(sjedis);
            return type;
        }

        public Set<String> keys(String pattern) {
            Jedis jedis = JedisClient.this.getJedis();
            Set<String> set = jedis.keys(pattern);
            JedisClient.this.returnJedis(jedis);
            return set;
        }
    }
}
