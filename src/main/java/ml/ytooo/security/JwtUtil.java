package ml.ytooo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Map;

/**
 * JWT 工具箱
 * Created by Youdmeng on 2019/2/22 0022.
 */
public class JwtUtil {

    private static Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private static final String PASSWORD = "";

    private static JwtUtil jwtUtil;

    @PostConstruct
    public void init() {
        jwtUtil = this;
    }

    /**
     * 生成token
     *
     * @return
     */
    public static String createJavaWebToken4JwtAuth(Map<String, Object> claims) {
        logger.info("生成的token为开始");
        if (!claims.containsKey("expireTime")) {
            claims.put("expireTime", System.currentTimeMillis() + (2 * 60 * 60 * 1000));
        }
        String toekn = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, getKeyInstance()).compact();
        logger.info("生成的token为：" + toekn);

        return toekn;
    }

    /**
     * 生成key
     *
     * @return
     */
    private static Key getKeyInstance() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        String apiKey = DatatypeConverter.printBase64Binary(PASSWORD.getBytes());
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        return signingKey;
    }


    /**
     * token是否过期
     *
     * @param jwt
     * @return
     */
    public static boolean isTokenExpire(String jwt) {
        if (StringUtils.isEmpty(jwt)) {
            return true;
        }
        Map<String, Object> claims = verifyJavaWebToken(jwt);
        if (null == claims) {
            logger.info("转换jwt失败！");
            return true;
        }
        try {
            if ((long) claims.get("expireTime") > System.currentTimeMillis()) {
                return false;
            }
        } catch (Exception ex) {
            logger.info("过期时间不存在或格式错误 ：" + claims.get("expireTime"));
        }
        return true;
    }

    /**
     * 获得body部分的信息
     *
     * @param jwt
     * @return
     */
    public static Map<String, Object> verifyJavaWebToken(String jwt) {
        try {
            Map<String, Object> jwtClaims =
                    Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwt).getBody();
            return jwtClaims;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("json web token verify failed");
            return null;
        }
    }

}
