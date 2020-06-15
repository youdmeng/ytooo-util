package ml.ytooo.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * JedisClusterConfig
 */
@Configuration
public class JedisClusterConfig {

    private static JedisClusterConfig instance;

    private JedisClusterConfig() {
    }

    public static JedisClusterConfig getInstance() {
        if (null == instance) {
            synchronized (JedisClusterConfig.class) {
                if (null == instance) {
                    instance = new JedisClusterConfig();
                }
            }
        }
        return instance;
    }

    /**
     * 注意：
     * 这里返回的JedisCluster是单例的，并且可以直接注入到其他类中去使用
     *
     * @return
     */
    public JedisCluster getJedisCluster() {

        //获取服务器数组(这里要相信自己的输入，所以没有考虑空指针问题)
        String[] serverArray = RedisProperties.clusterNodes.split(",");
        Set<HostAndPort> nodes = new HashSet<>();

        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }

        JedisCluster cluster = new JedisCluster(
                nodes,
                RedisProperties.connectionTimeout,
                RedisProperties.soTimeout,
                RedisProperties.maxAttempts,
                RedisProperties.password,
                new GenericObjectPoolConfig());

        return cluster;
    }

}
