package ml.ytooo.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 非对称加密算法RSA算法组件
 */
public class RSAUtils {

    //非对称密钥算法
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 公钥
     */
    private static String RSAPublicKey;

    /**
     * 私钥
     */
    private static String RSAPrivateKey;


    /**
     * 私钥解密
     *
     * @param encryptedData 已加密数据
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData)
            throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException, IOException {
        RSAPrivateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJaAgT+ZVpd9yLDCVGd91QPN6d33Le5yVm3LutW9f+g9NcYiZbnW9Yw7XXHthVnL/ZIhi4r8SVeoUhTwAACt5/wMeF7gj42mqfPH89fggPj+UibW4Ez9RFtdwxCcqDc2/sfNv8UKkej3RLIFTWZoJHNo2b8Hih2VIUizLDSlIDU7AgMBAAECgYAsUIA+GsddaeMK8N1rb6imKx5ehiaXHEGIqMFFqDueRU+tHHm5g6rfdKdqIVbvftoWChyNewWZcu1gTt6hVJOGtuvaTO51aQbN4N7sR5Rpxji3eD3qUKUGol3dWgLKXbpneBvT4WC3YgvJzR0Q/oERJBc43pyZNyNEh0U1q8FeaQJBAM1F94g6Aqu+G5aVj2RkZJWkk/k5+Vveqs2F0hOEsb4lReyWHhn8ZzpiRnh2nktloqO2aK4nZiNNIQUyfTDHzvcCQQC7sZW6GMdV30u7w9x9iQOLrmnfm6c2zELYiLTwbalquVxGqMiHALcLhl4BrVl9fuqY9NbjfrX2Wk7w0+9YjUbdAkAWMDXtd7fKMKZyxH/XbjKhKkUEb4vQrmTwCkMG8RT1ZLoGNMPUM0BFQ1Iyuz+pDrdh0tnF2WRk9HVZRnfZyf9JAkBBMo2tCCBt7tPS1FVJ8gceRSaXuuzZtxhdOReJEL6xyNOJ4+VMJ+5tfW44LOwSEL0TBrGCcqhoe7RCKYxReTmtAkB+hjdYMfFw76/flnOBWoSa/rD9FrYSLdPc13ty4VOeeSy0/Z8Y8Yf29xDeP2dwL8VKBzUrP6feMQomxy9sENqE";//ConfigUtil.getValue("RSAPrivateKey");
        if (null == RSAPrivateKey){
            return null;
        }
        byte[] keyBytes = Base64.decodeBase64(RSAPrivateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /**
     * 公钥加密
     *
     * @param data 源数据
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data)
            throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException, IOException {
        RSAPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCWgIE/mVaXfciwwlRnfdUDzend9y3uclZty7rVvX/oPTXGImW51vWMO11x7YVZy/2SIYuK/ElXqFIU8AAAref8DHhe4I+Npqnzx/PX4ID4/lIm1uBM/URbXcMQnKg3Nv7Hzb/FCpHo90SyBU1maCRzaNm/B4odlSFIsyw0pSA1OwIDAQAB";//ConfigUtil.getValue("RSAPublicKey");
        if (null == RSAPublicKey){
            return null;
        }
        byte[] keyBytes = Base64.decodeBase64(RSAPublicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;

    }

}
