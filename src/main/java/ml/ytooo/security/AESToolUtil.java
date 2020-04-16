package ml.ytooo.security;

import ml.ytooo.exception.ServiceException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * 数据AES加密|解密算法
 * @author Youdmeng
 * Date 2019-06-13
 **/
public class AESToolUtil {
    private static final Logger logger = LoggerFactory.getLogger(AESToolUtil.class);

    /**
     * 数据AES加密算法
     * @param encodeRules       加密规则
     * @param msg               加密信息
     * @return                  加密后数据
     */
    public static String encode(String encodeRules,String msg){
        if(StringUtils.isBlank(encodeRules)){
            throw new ServiceException("请输入正确加密编码");
        }
        if(StringUtils.isBlank(msg)){
            throw new ServiceException("请输入正确要编码code");
        }

        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen=KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
            secureRandom.setSeed(encodeRules.getBytes());
            keygen.init(128, secureRandom);
            //keygen.init(128, new SecureRandom(encodeRules.getBytes()));
            //3.产生原始对称密钥
            SecretKey original_key=keygen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte [] raw=original_key.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key=new SecretKeySpec(raw, "AES");
            //6.根据指定算法AES自成密码器
            Cipher cipher=Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte [] byteEncode=msg.getBytes(StandardCharsets.UTF_8);
            //9.根据密码器的初始化方式--加密：将数据加密
            byte [] byteAES = cipher.doFinal(byteEncode);
            //10.将加密后的数据转换为字符串
            //这里用Base64Encoder中会找不到包
            //解决办法：
            //在项目的Build path中先移除JRE System Library，再添加库JRE System Library，重新编译后就一切正常了。
            //11.将字符串返回
            return new String(new BASE64Encoder().encode(byteAES));
        } catch (Exception e){
            logger.error("数据AES加密失败", e);
            //加密失败
            throw new ServiceException("数据AES加密失败,请联系管理员["+e.getMessage()+"]");
        }
    }

    /**
     * 数据AES解密算法
     * @param encodeRules       解密规则
     * @param aesEncode         解密信息
     * @return                  解密后数据
     */
    public static String decode(String encodeRules,String aesEncode){
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen=KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
            secureRandom.setSeed(encodeRules.getBytes());
            keygen.init(128, secureRandom);
            //keygen.init(128, new SecureRandom(encodeRules.getBytes()));
            //3.产生原始对称密钥
            SecretKey original_key=keygen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte [] raw=original_key.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key=new SecretKeySpec(raw, "AES");
            //6.根据指定算法AES自成密码器
            Cipher cipher=Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key);
            //8.将加密并编码后的内容解码成字节数组
            byte [] byteContent= new BASE64Decoder().decodeBuffer(aesEncode);
            /*
             * 解密
             */
            byte [] byteDecode=cipher.doFinal(byteContent);
            return new String(byteDecode, StandardCharsets.UTF_8);
        } catch (Exception e){
            logger.error("数据AES解密失败", e);
            //解密失败
            throw new ServiceException("数据AES解密失败,请联系管理员["+e.getMessage()+"]");
        }
    }
    /**
     * 随机生成秘钥
     */
    public static String  getKey(){
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);//要生成多少位，只需要修改这里即可128, 192或256
            SecretKey sk = kg.generateKey();
            byte[] b = sk.getEncoded();
            return Base64.encodeBase64String(b);

        } catch (Exception e) {
            logger.error("生成 AES key失败 没有此算法。",e);
            throw new ServiceException("生成 AES key失败 没有此算法。");
        }
    }

}
