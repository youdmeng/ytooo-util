package ml.ytooo.redis;

import lombok.Data;

@Data
//@ConfigurationProperties(prefix = "redis.cache")
public class RedisProperties {

    public static int expireSeconds = 120;

    public static String clusterNodes = "192.168.55.117:20071";

    public static int connectionTimeout = 5000;

    public static String password = "tx1ypSLlnwcvjS99";

    public static int soTimeout = 2000;

    public static int maxAttempts = 3;
}
