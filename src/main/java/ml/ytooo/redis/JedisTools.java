package ml.ytooo.redis;

import ml.ytooo.redis.cluster.JedisClusterClient;
import ml.ytooo.redis.single.JedisClient;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisException;

import java.util.Collections;
import java.util.UUID;

/**
 * @author Youdmeng
 * @date 2021/2/26
 */
public class JedisTools {

    private static JedisTools tools;

    public static final int JEDIS_CLIENT = 1;
    public static final int JEDIS_CLUSTER = 2;

    private Integer linkType;

    private JedisTools() {
    }

    /**
     * 实例化方法
     *
     * @param type 连接类型 JEDIS_CLIENT ==> redis单点，JEDIS_CLUSTER ==> redis集群
     * @return 连接工具实例
     */
    public static JedisTools getInstance(int type) {
        if (null == tools) {
            synchronized (JedisTools.class) {
                if (null == tools) {
                    tools = new JedisTools();
                    if (JEDIS_CLIENT == type || JEDIS_CLUSTER == type) {
                        tools.linkType = type;
                    } else {
                        throw new JedisException("连接类异常!！");
                    }
                }
            }
        }
        return tools;
    }

    public JedisCommands getCommand() {
        if (JEDIS_CLIENT == this.linkType) {
            JedisClient jedisClient = JedisClient.getInstance();
            return jedisClient.getJedis();
        }
        if (JEDIS_CLUSTER == this.linkType) {
            JedisClusterClient jedisClusterClient = JedisClusterClient.getInstance();
            return jedisClusterClient.getJedisCluster();
        }
        return null;
    }

    public void closeRedis(JedisCommands commands) {
        if (null == commands) {
            return;
        }
        if (commands instanceof Jedis) {
            ((Jedis) commands).close();
        }
    }

    /**
     * 获取Redis锁
     *
     * @param lock    锁键
     * @param value   值
     * @param timeOut 超时时间
     */
    public boolean nxLock(String lock, String value, long timeOut) {
        JedisCommands command = getCommand();
        String status = command.set(lock, value, "NX", "EX", timeOut);
        closeRedis(command);
        return StringUtils.equals("OK", status);
    }

    /**
     * Redis锁 解锁
     *
     * @param lock  锁键
     * @param value 值
     */
    public boolean nxUnLock(String lock, String value) {
        JedisCommands command = getCommand();
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = null;
        if (command instanceof Jedis) {
            result = ((Jedis) command).eval(script, Collections.singletonList(lock), Collections.singletonList(value));
        } else if (command instanceof JedisCluster) {
            result = ((JedisCluster) command).eval(script, Collections.singletonList(lock), Collections.singletonList(value));
        } else {
            return false;
        }
        closeRedis(command);
        return StringUtils.equals(result.toString(), "0");
    }

    public static void main(String[] args) {

        JedisTools client = JedisTools.getInstance(JedisTools.JEDIS_CLIENT);

        String value = UUID.randomUUID().toString();
        System.out.println(client.nxLock("lock", value, 5000L));
        System.out.println(client.nxLock("lock", value, 5000L));
        System.out.println(client.nxLock("lock", value, 5000L));
        System.out.println(client.nxLock("lock", value, 5000L));
        System.out.println(client.nxLock("lock", value, 5000L));
        client.nxUnLock("lock", value);
        System.out.println(client.nxLock("lock", value, 5000L));
        System.out.println(client.nxLock("lock", value, 5000L));
        System.out.println(client.nxLock("lock", value, 5000L));
        client.nxUnLock("lock", value);

        client.getCommand().set("lll", "124");
    }

}
