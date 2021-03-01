package ml.ytooo.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;


public class DESUtil {

    public static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";

    public static final String KEY = "12345678";

    public static final String BHKEY = "djnk2645";

    /**
     * DES算法，加密
     *
     * @param data 待加密字符串
     * @param key  加密私钥，长度不能够小于8位
     * @return 加密后的字节数组，一般结合Base64编码使用
     */
    public static String encode(String key, String data) throws Exception {
        return encode(key, data.getBytes("gbk"));
    }

    public static String encode(String data) throws Exception {
        return encode(KEY, data.getBytes("gbk"));
    }

    /**
     * DES算法，加密
     *
     * @param data 待加密字符串
     * @param key  加密私钥，长度不能够小于8位
     * @return 加密后的字节数组，一般结合Base64编码使用
     * @throws CryptException 异常
     */
    public static String encode(String key, byte[] data) throws Exception {
        try {
            DESKeySpec dks = new DESKeySpec(key.getBytes());

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);

            byte[] bytes = cipher.doFinal(data);
            return Base64.encodeBase64String(bytes);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * DES算法，解密
     *
     * @param data 待解密字符串
     * @param key  解密私钥，长度不能够小于8位
     * @return 解密后的字节数组
     * @throws Exception 异常
     */
    public static byte[] greatLifeDecode(String key, byte[] data) throws Exception {
        try {
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            IvParameterSpec iv = new IvParameterSpec(key.getBytes());
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * DES算法，解密
     *
     * @param data 待解密字符串
     * @param key  解密私钥，长度不能够小于8位
     * @return 解密后的字节数组
     * @throws Exception 异常
     */
    public static byte[] decode(String key, byte[] data) throws Exception {
        try {
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 获取编码后的值
     *
     * @param key
     * @param data
     * @return
     * @throws Exception
     */
    public static String decodeValue(String key, String data) {
        byte[] datas;
        String value = null;
        try {
            if (System.getProperty("os.name") != null
                    && ("sunos".equalsIgnoreCase(System.getProperty("os.name")) || "linux"
                    .equalsIgnoreCase(System.getProperty("os.name")))) {
                datas = decode(key, Base64.decodeBase64(data));
            } else {
                datas = decode(key, Base64.decodeBase64(data));
            }

            value = new String(datas, "gbk");
        } catch (Exception e) {
            e.printStackTrace();
            value = "";
        }
        return value;
    }

    public static String decode(String data) {
        return decodeValue(KEY, data);
    }

    public static String bhLifeDecode(String key, String data) {
        return bhLifePolicyDecodeValue(key, data);
    }

    public static String bhLifeDecode(String data) {
        return bhLifePolicyDecodeValue(BHKEY, data);
    }

    public static String bhLifePolicyEncoder(String key, String data) throws Exception {
        try {
            DESKeySpec dks = new DESKeySpec(key.getBytes());

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            IvParameterSpec iv = new IvParameterSpec(key.getBytes());
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);

            byte[] bytes = cipher.doFinal(data.getBytes("gbk"));
            return Base64.encodeBase64String(bytes);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static String bhLifePolicyEncoder(String data) throws Exception {
        try {
            DESKeySpec dks = new DESKeySpec(BHKEY.getBytes());

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            IvParameterSpec iv = new IvParameterSpec(BHKEY.getBytes());
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);

            byte[] bytes = cipher.doFinal(data.getBytes("gbk"));
            return Base64.encodeBase64String(bytes);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 获取编码后的值
     *
     * @param key
     * @param data
     * @return
     * @throws Exception
     */
    public static String bhLifePolicyDecodeValue(String key, String data) {
        byte[] datas;
        String value = null;
        try {
            if (System.getProperty("os.name") != null
                    && (System.getProperty("os.name").equalsIgnoreCase("sunos") || System.getProperty("os.name")
                    .equalsIgnoreCase("linux"))) {
                datas = greatLifeDecode(key, Base64.decodeBase64(data));
            } else {
                datas = greatLifeDecode(key, Base64.decodeBase64(data));
            }

            value = new String(datas, "gbk");
        } catch (Exception e) {
            e.printStackTrace();
            value = "";
        }
        return value;
    }

    /**
     * 转换byte数组为16进制字符串, 逆操作方法是hexStrToByte
     *
     * @param keyByte
     * @return
     */
    public static String byteToHexStr(byte[] keyByte) {
        StringBuilder hexByteStr = new StringBuilder("");
        for (int i = 0; i < keyByte.length; i++) {
            String rHexStr = Integer.toHexString(keyByte[i] + 128);
            if (rHexStr.length() < 2) {
                rHexStr = "0" + rHexStr;
            }
            hexByteStr.append(rHexStr);
        }
        return hexByteStr.toString();
    }

    /**
     * 转换16进制字符串为byte数组,逆操作方法是byteToHexStr
     *µ
     * @param keyByteStr
     * @return
     */
    public static byte[] hexStrToByte(String keyByteStr) {
        int byteLen = keyByteStr.length() / 2;
        byte[] rByteA = new byte[byteLen];
        for (int i = 0; i < keyByteStr.length(); i += 2) {
            rByteA[i / 2] = Byte
                    .parseByte(""
                            + (Integer.parseInt(keyByteStr.substring(i, i + 2),
                            16) - 128));
        }
        return rByteA;
    }

    public static void main(String[] args) throws Exception {

        // System.out.println("明：abc ；密：" + Des2.encode("12345678","abc"));
        // System.out.println("明：ABC ；密：" + Des2.encode("12345678","ABC"));
        // //System.out.println("明：中国人 ；密：" + Des2.encode("12345678","中国人"));
        // System.out.println("g3ME4dIH/5dpd1M mHqZ2eWk8pxuq8V5=" + Des2.decodeValue("88888888",
        // "g3ME4dIH/5dpd1MmHqZ2eWk8pxuq8V5"));
        // System.out.println("ss=" + Des2.decodeValue("88888888",
        // "rc+uwHsk8I4SGFElQ4NMQbZgdrPwMSNrfv0Pt5RQ15/2q+i7jd6JFOgahlPhhGDuohv5MS7K8UZiVxP7Zp796Y0FQPJu58wx5LhlHUEZCUXcHAKnjX0EsQSrpfmIF/JZxMfcGCNWCABDTRQG8oDs2CMYGI/g2cmL9nU6EpG4RGH9zhx9Gsovv/mnxKKqLI9fySUfAaMxReEZoUWpEFPKk7xvV7mxQsUNNaaCekCt0D+FP65bQBweZA=="));

        // System.out.println("明：在那遥远的地方 ；密：" + Des2.encode("12345678","g3ME4dIH/5dpd1M mHqZ2eWk8pxuq8V5"));
        // System.out.println(new String(Des2.decode("wwwid5cn",
        // "ABB0E340805D367C438E24FC4005C121F60247F6EE4430B5".toLowerCase().getBytes())));
        System.out.println("dd=" + encode("12345678", new String("idtagpckhd")));
        // System.out.println("dd=" + Des2.encode("88888888", new String("周慧".getBytes("GBK"),"GBK")));
        System.out.println("dd=" + decodeValue("12345678", "yPJEpR5wlVn35Ag+YyRsSw=="));

        String hexStr = byteToHexStr("顾老师是牛人123（）".getBytes("gbk"));
        System.out.println(hexStr);
        System.out.println(new String(hexStrToByte(hexStr), "gbk"));
    }
}
