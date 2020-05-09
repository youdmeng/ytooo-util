package ml.ytooo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ml.ytooo.time.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 工具箱
 * Created by Youdmeng on 2019/2/22 0022.
 */
public class JwtUtil {

    private static final  Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private static final String SALT = "0142add7c2664198863943f24bf4b8b9";


    public static void main(String[] args)  {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", "大西瓜");
        paramMap.put("dept", "冬西瓜地");
        String tokens = JwtUtil.createJavaWebToken4JwtAuth(paramMap);
        System.out.println(tokens);
        System.out.println(isTokenEffect(tokens));

    }

    /**
     * 生成token
     *
     * @return
     */
    public static String createJavaWebToken4JwtAuth(Map<String, Object> claims) {
        logger.info("生成的token为开始");
        String toekn = Jwts.builder().setClaims(claims).setExpiration(DateUtil.addSeconds(new Date(), 50))
                .signWith(SignatureAlgorithm.HS256, getKeyInstance()).compact();
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
        String apiKey = DatatypeConverter.printBase64Binary(SALT.getBytes());
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        return signingKey;
    }


    /**
     * token是否有效
     *
     * @param jwt
     * @return
     */
    public static boolean isTokenEffect(String jwt) {
        if (StringUtils.isEmpty(jwt)) {
            return false;
        }
        Map<String, Object> claims = verifyJavaWebToken(jwt);
        if (null == claims) {
            logger.info("转换jwt失败！");
            return false;
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
            logger.info(e.getMessage());
            return null;
        }
    }

}
