package ml.ytooo.redis;

import lombok.Data;

@Data
public class RedisProperties {

    //================集群========================
    public static int expireSeconds = 120;

    public static String clusterNodes = "127.0.0.1:6379";

    public static int connectionTimeout = 5000;

    public static int soTimeout = 2000;

    public static int maxAttempts = 3;

    public static String password = "";

    //================单节点========================
    public static String IP = "127.0.0.1";

    public static int PORT = 6379;
}
