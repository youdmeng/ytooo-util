//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ytooo.redis;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.SortingParams;
import redis.clients.util.SafeEncoder;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisUtil {
    private final int expire = 60000;
    public JedisUtil.Keys KEYS;
    public JedisUtil.Strings STRINGS;
    public JedisUtil.Lists LISTS;
    public JedisUtil.Sets SETS;
    public JedisUtil.Hash HASH;
    public JedisUtil.SortSet SORTSET;
    private static JedisPool jedisPool = null;
    private static final JedisUtil jedisUtil;

    private JedisUtil() {
    }

    public JedisPool getPool() {
        return jedisPool;
    }

    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    public static JedisUtil getInstance() {
        return jedisUtil;
    }

    public void returnJedis(Jedis jedis) {
        if (null != jedis && null != jedisPool) {
            jedis.close();
        }

    }

    public static void returnBrokenResource(Jedis jedis) {
        if (null != jedis && null != jedisPool) {
            jedis.close();
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
        config.setMaxTotal(JRedisPoolConfig.maxTotal);
        config.setMaxIdle(JRedisPoolConfig.maxIdle);
        config.setMaxWaitMillis(JRedisPoolConfig.maxWaitMillis);
        config.setTestOnBorrow(JRedisPoolConfig.testOnBorrow);
        if (JRedisPoolConfig.REDIS_PASSWORD != null && !"".equals(JRedisPoolConfig.REDIS_PASSWORD)) {
            jedisPool = new JedisPool(config, JRedisPoolConfig.REDIS_IP, JRedisPoolConfig.REDIS_PORT, 10000, JRedisPoolConfig.REDIS_PASSWORD);
        } else {
            jedisPool = new JedisPool(config, JRedisPoolConfig.REDIS_IP, JRedisPoolConfig.REDIS_PORT);
        }

        jedisUtil = new JedisUtil();
    }

    public class Keys {
        public Keys() {
        }

        public String flushAll(int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            String stata = jedis.flushAll();
            JedisUtil.this.returnJedis(jedis);
            return stata;
        }

        public String rename(String oldkey, String newkey, int dbNum) {
            return this.rename(SafeEncoder.encode(oldkey), SafeEncoder.encode(newkey), dbNum);
        }

        public long renamenx(String oldkey, String newkey, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long status = jedis.renamenx(oldkey, newkey);
            JedisUtil.this.returnJedis(jedis);
            return status;
        }

        public String rename(byte[] oldkey, byte[] newkey, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            String status = jedis.rename(oldkey, newkey);
            JedisUtil.this.returnJedis(jedis);
            return status;
        }

        public long expired(String key, int seconds, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long count = jedis.expire(key, seconds);
            JedisUtil.this.returnJedis(jedis);
            return count;
        }

        public long expireAt(String key, long timestamp, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long count = jedis.expireAt(key, timestamp);
            JedisUtil.this.returnJedis(jedis);
            return count;
        }

        public long ttl(String key, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            long len = sjedis.ttl(key);
            JedisUtil.this.returnJedis(sjedis);
            return len;
        }

        public long persist(String key, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long count = jedis.persist(key);
            JedisUtil.this.returnJedis(jedis);
            return count;
        }

        public long del(int dbNum,String... keys) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long count = jedis.del(keys);
            JedisUtil.this.returnJedis(jedis);
            return count;
        }

        public long del(int dbNum, byte[]... keys) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long count = jedis.del(keys);
            JedisUtil.this.returnJedis(jedis);
            return count;
        }

        public boolean exists(String key, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            boolean exis = sjedis.exists(key);
            JedisUtil.this.returnJedis(sjedis);
            return exis;
        }

        public List<String> sort(String key, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            List list = sjedis.sort(key);
            JedisUtil.this.returnJedis(sjedis);
            return list;
        }

        public List<String> sort(String key, SortingParams parame, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            List list = sjedis.sort(key, parame);
            JedisUtil.this.returnJedis(sjedis);
            return list;
        }

        public String type(String key, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            String type = sjedis.type(key);
            JedisUtil.this.returnJedis(sjedis);
            return type;
        }

        public Set<String> keys(String pattern, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            Set set = jedis.keys(pattern);
            JedisUtil.this.returnJedis(jedis);
            return set;
        }
    }

    public class Sets {
        public Sets() {
        }

        public long sadd(String key, String member, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long s = jedis.sadd(key, new String[]{member});
            JedisUtil.this.returnJedis(jedis);
            return s;
        }

        public long sadd(byte[] key, byte[] member, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long s = jedis.sadd(key, new byte[][]{member});
            JedisUtil.this.returnJedis(jedis);
            return s;
        }

        public long scard(String key, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            long len = sjedis.scard(key);
            JedisUtil.this.returnJedis(sjedis);
            return len;
        }

        public Set<String> sdiff(int dbNum,String... keys) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            Set set = jedis.sdiff(keys);
            JedisUtil.this.returnJedis(jedis);
            return set;
        }

        public long sdiffstore(int dbNum, String newkey, String... keys) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long s = jedis.sdiffstore(newkey, keys);
            JedisUtil.this.returnJedis(jedis);
            return s;
        }

        public Set<String> sinter(int dbNum, String... keys) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            Set set = jedis.sinter(keys);
            JedisUtil.this.returnJedis(jedis);
            return set;
        }

        public long sinterstore(int dbNum, String newkey, String... keys) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long s = jedis.sinterstore(newkey, keys);
            JedisUtil.this.returnJedis(jedis);
            return s;
        }

        public boolean sismember(String key, String member, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            boolean s = sjedis.sismember(key, member);
            JedisUtil.this.returnJedis(sjedis);
            return s;
        }

        public Set<String> smembers(String key, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            Set set = sjedis.smembers(key);
            JedisUtil.this.returnJedis(sjedis);
            return set;
        }

        public Set<byte[]> smembers(byte[] key, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            Set set = sjedis.smembers(key);
            JedisUtil.this.returnJedis(sjedis);
            return set;
        }

        public long smove(String srckey, String dstkey, String member, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long s = jedis.smove(srckey, dstkey, member);
            JedisUtil.this.returnJedis(jedis);
            return s;
        }

        public String spop(String key, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            String s = jedis.spop(key);
            JedisUtil.this.returnJedis(jedis);
            return s;
        }

        public long srem(String key, String member, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long s = jedis.srem(key, new String[]{member});
            JedisUtil.this.returnJedis(jedis);
            return s;
        }

        public Set<String> sunion(int dbNum,String... keys) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            Set set = jedis.sunion(keys);
            JedisUtil.this.returnJedis(jedis);
            return set;
        }

        public long sunionstore(String newkey, int dbNum, String... keys) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long s = jedis.sunionstore(newkey, keys);
            JedisUtil.this.returnJedis(jedis);
            return s;
        }
    }

    public class SortSet {
        public SortSet() {
        }

        public long zadd(String key, double score, String member, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long s = jedis.zadd(key, score, member);
            JedisUtil.this.returnJedis(jedis);
            return s;
        }

        public long zcard(String key, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            long len = sjedis.zcard(key);
            JedisUtil.this.returnJedis(sjedis);
            return len;
        }

        public long zcount(String key, double min, double max, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            long len = sjedis.zcount(key, min, max);
            JedisUtil.this.returnJedis(sjedis);
            return len;
        }

        public long zlength(String key, int dbNum) {
            long len = 0L;
            Set set = this.zrange(key, 0, -1, dbNum);
            len = (long)set.size();
            return len;
        }

        public double zincrby(String key, double score, String member, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            double s = jedis.zincrby(key, score, member);
            JedisUtil.this.returnJedis(jedis);
            return s;
        }

        public Set<String> zrange(String key, int start, int end, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            Set set = sjedis.zrange(key, (long)start, (long)end);
            JedisUtil.this.returnJedis(sjedis);
            return set;
        }

        public Set<String> zrangeByScore(String key, double min, double max, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            Set set = sjedis.zrangeByScore(key, min, max);
            JedisUtil.this.returnJedis(sjedis);
            return set;
        }

        public long zrank(String key, String member, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            long index = sjedis.zrank(key, member);
            JedisUtil.this.returnJedis(sjedis);
            return index;
        }

        public long zrevrank(String key, String member, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            long index = sjedis.zrevrank(key, member);
            JedisUtil.this.returnJedis(sjedis);
            return index;
        }

        public long zrem(String key, String member, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long s = jedis.zrem(key, new String[]{member});
            JedisUtil.this.returnJedis(jedis);
            return s;
        }

        public long zrem(String key, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long s = jedis.del(key);
            JedisUtil.this.returnJedis(jedis);
            return s;
        }

        public long zremrangeByRank(String key, int start, int end, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long s = jedis.zremrangeByRank(key, (long)start, (long)end);
            JedisUtil.this.returnJedis(jedis);
            return s;
        }

        public long zremrangeByScore(String key, double min, double max, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long s = jedis.zremrangeByScore(key, min, max);
            JedisUtil.this.returnJedis(jedis);
            return s;
        }

        public Set<String> zrevrange(String key, int start, int end, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            Set set = sjedis.zrevrange(key, (long)start, (long)end);
            JedisUtil.this.returnJedis(sjedis);
            return set;
        }

        public double zscore(String key, String memebr, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            Double score = sjedis.zscore(key, memebr);
            JedisUtil.this.returnJedis(sjedis);
            return score != null ? score : 0.0D;
        }
    }

    public class Hash {
        public Hash() {
        }

        public long hdel(String key, String fieid, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long s = jedis.hdel(key, new String[]{fieid});
            JedisUtil.this.returnJedis(jedis);
            return s;
        }

        public long hdel(String key, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long s = jedis.del(key);
            JedisUtil.this.returnJedis(jedis);
            return s;
        }

        public boolean hexists(String key, String fieid, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            boolean s = sjedis.hexists(key, fieid);
            JedisUtil.this.returnJedis(sjedis);
            return s;
        }

        public String hget(String key, String fieid, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            String s = sjedis.hget(key, fieid);
            JedisUtil.this.returnJedis(sjedis);
            return s;
        }

        public byte[] hget(byte[] key, byte[] fieid, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            byte[] s = sjedis.hget(key, fieid);
            JedisUtil.this.returnJedis(sjedis);
            return s;
        }

        public Map<String, String> hgetAll(String key, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            Map map = sjedis.hgetAll(key);
            JedisUtil.this.returnJedis(sjedis);
            return map;
        }

        public long hset(String key, String fieid, String value, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long s = jedis.hset(key, fieid, value);
            JedisUtil.this.returnJedis(jedis);
            return s;
        }

        public long hset(String key, String fieid, byte[] value, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long s = jedis.hset(key.getBytes(), fieid.getBytes(), value);
            JedisUtil.this.returnJedis(jedis);
            return s;
        }

        public long hsetnx(String key, String fieid, String value, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long s = jedis.hsetnx(key, fieid, value);
            JedisUtil.this.returnJedis(jedis);
            return s;
        }

        public List<String> hvals(String key, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            List list = sjedis.hvals(key);
            JedisUtil.this.returnJedis(sjedis);
            return list;
        }

        public long hincrby(String key, String fieid, long value, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long s = jedis.hincrBy(key, fieid, value);
            JedisUtil.this.returnJedis(jedis);
            return s;
        }

        public Set<String> hkeys(String key, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            Set set = sjedis.hkeys(key);
            JedisUtil.this.returnJedis(sjedis);
            return set;
        }

        public long hlen(String key, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            long len = sjedis.hlen(key);
            JedisUtil.this.returnJedis(sjedis);
            return len;
        }

        public List<String> hmget(String key, int dbNum, String... fieids) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            List list = sjedis.hmget(key, fieids);
            JedisUtil.this.returnJedis(sjedis);
            return list;
        }

        public List<byte[]> hmget(byte[] key, int dbNum, byte[]... fieids) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            List list = sjedis.hmget(key, fieids);
            JedisUtil.this.returnJedis(sjedis);
            return list;
        }

        public String hmset(String key, Map<String, String> map, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            String s = jedis.hmset(key, map);
            JedisUtil.this.returnJedis(jedis);
            return s;
        }

        public String hmset(byte[] key, Map<byte[], byte[]> map, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            String s = jedis.hmset(key, map);
            JedisUtil.this.returnJedis(jedis);
            return s;
        }
    }

    public class Strings {
        public Strings() {
        }

        public String get(String key, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            String value = sjedis.get(key);
            JedisUtil.this.returnJedis(sjedis);
            return value;
        }

        public byte[] get(byte[] key, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            byte[] value = sjedis.get(key);
            JedisUtil.this.returnJedis(sjedis);
            return value;
        }

        public String setEx(String key, int seconds, String value, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            String str = jedis.setex(key, seconds, value);
            JedisUtil.this.returnJedis(jedis);
            return str;
        }

        public String setEx(byte[] key, int seconds, byte[] value, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            String str = jedis.setex(key, seconds, value);
            JedisUtil.this.returnJedis(jedis);
            return str;
        }

        public long setnx(String key, String value, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long str = jedis.setnx(key, value);
            JedisUtil.this.returnJedis(jedis);
            return str;
        }

        public String set(String key, String value, int dbNum) {
            return this.set(SafeEncoder.encode(key), SafeEncoder.encode(value), dbNum);
        }

        public String set(String key, byte[] value,int dbNum) {
            return this.set(SafeEncoder.encode(key), value, dbNum);
        }

        public String set(byte[] key, byte[] value, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            String status = jedis.set(key, value);
            JedisUtil.this.returnJedis(jedis);
            return status;
        }

        public long setRange(String key, long offset, String value, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long len = jedis.setrange(key, offset, value);
            JedisUtil.this.returnJedis(jedis);
            return len;
        }

        public long append(String key, String value, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long len = jedis.append(key, value);
            JedisUtil.this.returnJedis(jedis);
            return len;
        }

        public long decrBy(String key, long number, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long len = jedis.decrBy(key, number);
            JedisUtil.this.returnJedis(jedis);
            return len;
        }

        public long incrBy(String key, long number, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long len = jedis.incrBy(key, number);
            JedisUtil.this.returnJedis(jedis);
            return len;
        }

        public String getrange(String key, long startOffset, long endOffset, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            String value = sjedis.getrange(key, startOffset, endOffset);
            JedisUtil.this.returnJedis(sjedis);
            return value;
        }

        public String getSet(String key, String value, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            String str = jedis.getSet(key, value);
            JedisUtil.this.returnJedis(jedis);
            return str;
        }

        public List<String> mget(int dbNum, String... keys) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            List str = jedis.mget(keys);
            JedisUtil.this.returnJedis(jedis);
            return str;
        }

        public String mset(int dbNum, String... keysvalues) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            String str = jedis.mset(keysvalues);
            JedisUtil.this.returnJedis(jedis);
            return str;
        }

        public long strlen(String key, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long len = jedis.strlen(key);
            JedisUtil.this.returnJedis(jedis);
            return len;
        }
    }

    public class Lists {
        public Lists() {
        }

        public long llen(String key, int dbNum) {
            return this.llen(SafeEncoder.encode(key),dbNum);
        }

        public long llen(byte[] key, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            long count = sjedis.llen(key);
            JedisUtil.this.returnJedis(sjedis);
            return count;
        }

        public String lset(byte[] key, int index, byte[] value, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            String status = jedis.lset(key, (long)index, value);
            JedisUtil.this.returnJedis(jedis);
            return status;
        }

        public String lset(String key, int index, String value, int dbNum) {
            return this.lset(SafeEncoder.encode(key), index, SafeEncoder.encode(value), dbNum);
        }

        public long linsert(String key, LIST_POSITION where, String pivot, String value, int dbNum) {
            return this.linsert(SafeEncoder.encode(key), where, SafeEncoder.encode(pivot), SafeEncoder.encode(value), dbNum);
        }

        public long linsert(byte[] key, LIST_POSITION where, byte[] pivot, byte[] value, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long count = jedis.linsert(key, where, pivot, value);
            JedisUtil.this.returnJedis(jedis);
            return count;
        }

        public String lindex(String key, int index, int dbNum) {
            return SafeEncoder.encode(this.lindex(SafeEncoder.encode(key), index, dbNum));
        }

        public byte[] lindex(byte[] key, int index, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            byte[] value = sjedis.lindex(key, (long)index);
            JedisUtil.this.returnJedis(sjedis);
            return value;
        }

        public String lpop(String key, int dbNum) {
            return SafeEncoder.encode(this.lpop(SafeEncoder.encode(key), dbNum));
        }

        public byte[] lpop(byte[] key, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            byte[] value = jedis.lpop(key);
            JedisUtil.this.returnJedis(jedis);
            return value;
        }

        public String rpop(String key, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            String value = jedis.rpop(key);
            JedisUtil.this.returnJedis(jedis);
            return value;
        }

        public long lpush(String key, String value, int dbNum) {
            return this.lpush(SafeEncoder.encode(key), SafeEncoder.encode(value), dbNum);
        }

        public long rpush(String key, String value, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long count = jedis.rpush(key, new String[]{value});
            JedisUtil.this.returnJedis(jedis);
            return count;
        }

        public long rpush(byte[] key, byte[] value, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long count = jedis.rpush(key, new byte[][]{value});
            JedisUtil.this.returnJedis(jedis);
            return count;
        }

        public long lpush(byte[] key, byte[] value, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long count = jedis.lpush(key, new byte[][]{value});
            JedisUtil.this.returnJedis(jedis);
            return count;
        }

        public List<String> lrange(String key, long start, long end, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            List list = sjedis.lrange(key, start, end);
            JedisUtil.this.returnJedis(sjedis);
            return list;
        }

        public List<byte[]> lrange(byte[] key, int start, int end, int dbNum) {
            Jedis sjedis = JedisUtil.this.getJedis();
            sjedis.select(dbNum);
            List list = sjedis.lrange(key, (long)start, (long)end);
            JedisUtil.this.returnJedis(sjedis);
            return list;
        }

        public long lrem(byte[] key, int c, byte[] value, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            long count = jedis.lrem(key, (long)c, value);
            JedisUtil.this.returnJedis(jedis);
            return count;
        }

        public long lrem(String key, int c, String value, int dbNum) {
            return this.lrem(SafeEncoder.encode(key), c, SafeEncoder.encode(value), dbNum);
        }

        public String ltrim(byte[] key, int start, int end, int dbNum) {
            Jedis jedis = JedisUtil.this.getJedis();
            jedis.select(dbNum);
            String str = jedis.ltrim(key, (long)start, (long)end);
            JedisUtil.this.returnJedis(jedis);
            return str;
        }

        public String ltrim(String key, int start, int end, int dbNum) {
            return this.ltrim(SafeEncoder.encode(key), start, end, dbNum);
        }
    }
}
