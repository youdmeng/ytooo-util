package ml.ytooo.security;

import com.alibaba.fastjson.JSON;
import ml.ytooo.exception.ServiceException;
import ml.ytooo.security.bean.SecurityBean;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * AES 结合 RSA 加密算法
 * Created by Youdmeng on 2019/6/13 0013.
 */
public class AesWithRsaUtil {

    private static final Logger logger = LoggerFactory.getLogger(AesWithRsaUtil.class);

    /**
     * 对数据进行加密
     *
     * @param data
     * @return
     * @author Youdmeng
     * Date 2019-06-13
     **/
    public static SecurityBean encode(String data) {
        //生成aes key
        String key = AESToolUtil.getKey();
        //数据使用AES加密
        String baseStr = AESToolUtil.encode(key, data);
        //使用RSA 对key加密
        String keySecurity = null;
        try {
            keySecurity = Base64.encodeBase64String(RSAUtils.encryptByPublicKey(key.getBytes()));
        } catch (Exception e) {
            logger.error("==========数据加密失败 AES key加密失败===========", e);
            throw new ServiceException("数据加密失败 AES key加密失败");
        }
        return new SecurityBean(keySecurity, baseStr);
    }

    /**
     * 对数据进行解密
     * @param bean
     * @return
     * @author Youdmeng
     * Date 2019-06-13
     **/
    public static String decode(SecurityBean bean) {
        logger.info("数据解密开始 参数为:" + JSON.toJSONString(bean));
        Date start = new Date();
        String keySecurity = bean.getKey();
        String baseStr = bean.getBaseStr();
        //使用 RSA 解密key
        String key = null;
        try {
            key = new String(RSAUtils.decryptByPrivateKey(Base64.decodeBase64(keySecurity)));
        } catch (Exception e) {
            logger.error("==========数据解密失败 AES key解密失败===========", e);
            throw new ServiceException("数据解密失败 AES key解密失败");
        }
        String str = AESToolUtil.decode(key, baseStr);
        Date end = new Date();
        logger.info("数据解密结束 用时:" + ((end.getTime() - start.getTime()) / 1000 + "秒"));
        return str;
    }

    public static void main(String[] args) {
        String json = "{\"commercialRuleInfo\":{\"aliasId\":0,\"ccs5\":\"string\",\"createTime\":\"2019-06-13T03:34:59.371Z\",\"discount\":0,\"garageId\":0,\"id\":0,\"orgId\":0,\"price\":0,\"priceWorkhourId\":0,\"totalPrice\":0,\"updateTime\":\"2019-06-13T03:34:59.371Z\",\"vecMod\":\"string\"},\"garageAliasEO\":{\"accountBank\":\"string\",\"aliasName\":\"string\",\"bankAccount\":\"string\",\"businessEnd\":\"2019-06-13T03:34:59.371Z\",\"businessStart\":\"2019-06-13T03:34:59.371Z\",\"createTime\":\"2019-06-13T03:34:59.371Z\",\"delFlag\":0,\"garageCompensationFlag\":\"string\",\"garageCooperationFlag\":\"string\",\"garageId\":0,\"id\":0,\"orgId\":0,\"partValid\":\"2019-06-13T03:34:59.371Z\",\"reuse\":0,\"taxNo\":\"string\",\"timeValid\":\"2019-06-13T03:34:59.371Z\",\"updateTime\":\"2019-06-13T03:34:59.371Z\",\"useNumber\":0},\"garageBrandsEOs\":[{\"automakerId\":\"string\",\"createTime\":\"2019-06-13T03:34:59.371Z\",\"delFlag\":0,\"garageId\":0,\"id\":0,\"updateTime\":\"2019-06-13T03:34:59.371Z\"}],\"garageEO\":{\"address\":\"string\",\"businessLicenseNo\":\"string\",\"chargeOrg\":0,\"chargeTime\":\"2019-06-13T03:34:59.371Z\",\"cityId\":\"string\",\"createTime\":\"2019-06-13T03:34:59.371Z\",\"creditCode\":\"string\",\"declareInsuranceComp\":\"string\",\"delFlag\":0,\"districtId\":\"string\",\"fullAddress\":\"string\",\"garageCode\":\"string\",\"garageLevel\":\"string\",\"garageMajorFlag\":\"string\",\"garageName\":\"string\",\"garageNo\":\"string\",\"garageOrgCode\":\"string\",\"id\":0,\"latitude\":\"string\",\"longitude\":\"string\",\"orgId\":0,\"phone\":\"string\",\"provinceId\":\"string\",\"representative\":\"string\",\"reuse\":0,\"serialNo\":\"string\",\"status\":\"string\",\"updateTime\":\"2019-06-13T03:34:59.371Z\",\"useNumber\":0},\"partsRuleEOs\":[{\"aliasId\":0,\"alies\":\"string\",\"createTime\":\"2019-06-13T03:34:59.371Z\",\"delFlag\":0,\"discount\":0,\"garageId\":0,\"id\":0,\"orgId\":0,\"priceType\":0,\"updateTime\":\"2019-06-13T03:34:59.371Z\"}],\"passengerRuleInfo\":{\"aliasId\":0,\"ccs5\":\"string\",\"createTime\":\"2019-06-13T03:34:59.371Z\",\"discount\":0,\"garageId\":0,\"id\":0,\"orgId\":0,\"price\":0,\"priceWorkhourId\":0,\"totalPrice\":0,\"updateTime\":\"2019-06-13T03:34:59.371Z\",\"vecMod\":\"string\"},\"workhourRuleVOs\":[{\"aliasId\":0,\"ccs5\":\"string\",\"createTime\":\"2019-06-13T03:34:59.371Z\",\"discount\":0,\"garageId\":0,\"id\":0,\"orgId\":0,\"price\":0,\"priceWorkhourId\":0,\"totalPrice\":0,\"updateTime\":\"2019-06-13T03:34:59.371Z\",\"vecMod\":\"string\"}]}";
        System.out.println("加密前:" + json);
        SecurityBean bean = AesWithRsaUtil.encode(json);
        String jsonBack = AesWithRsaUtil.decode(bean);
        System.out.println("加密后:" + jsonBack);

    }

//    public static void main(String[] args) throws Exception {
//        String json = "{\"commercialRuleInfo\":{\"aliasId\":0,\"ccs5\":\"string\",\"createTime\":\"2019-06-13T03:34:59.371Z\",\"discount\":0,\"garageId\":0,\"id\":0,\"orgId\":0,\"price\":0,\"priceWorkhourId\":0,\"totalPrice\":0,\"updateTime\":\"2019-06-13T03:34:59.371Z\",\"vecMod\":\"string\"},\"garageAliasEO\":{\"accountBank\":\"string\",\"aliasName\":\"string\",\"bankAccount\":\"string\",\"businessEnd\":\"2019-06-13T03:34:59.371Z\",\"businessStart\":\"2019-06-13T03:34:59.371Z\",\"createTime\":\"2019-06-13T03:34:59.371Z\",\"delFlag\":0,\"garageCompensationFlag\":\"string\",\"garageCooperationFlag\":\"string\",\"garageId\":0,\"id\":0,\"orgId\":0,\"partValid\":\"2019-06-13T03:34:59.371Z\",\"reuse\":0,\"taxNo\":\"string\",\"timeValid\":\"2019-06-13T03:34:59.371Z\",\"updateTime\":\"2019-06-13T03:34:59.371Z\",\"useNumber\":0},\"garageBrandsEOs\":[{\"automakerId\":\"string\",\"createTime\":\"2019-06-13T03:34:59.371Z\",\"delFlag\":0,\"garageId\":0,\"id\":0,\"updateTime\":\"2019-06-13T03:34:59.371Z\"}],\"garageEO\":{\"address\":\"string\",\"businessLicenseNo\":\"string\",\"chargeOrg\":0,\"chargeTime\":\"2019-06-13T03:34:59.371Z\",\"cityId\":\"string\",\"createTime\":\"2019-06-13T03:34:59.371Z\",\"creditCode\":\"string\",\"declareInsuranceComp\":\"string\",\"delFlag\":0,\"districtId\":\"string\",\"fullAddress\":\"string\",\"garageCode\":\"string\",\"garageLevel\":\"string\",\"garageMajorFlag\":\"string\",\"garageName\":\"string\",\"garageNo\":\"string\",\"garageOrgCode\":\"string\",\"id\":0,\"latitude\":\"string\",\"longitude\":\"string\",\"orgId\":0,\"phone\":\"string\",\"provinceId\":\"string\",\"representative\":\"string\",\"reuse\":0,\"serialNo\":\"string\",\"status\":\"string\",\"updateTime\":\"2019-06-13T03:34:59.371Z\",\"useNumber\":0},\"partsRuleEOs\":[{\"aliasId\":0,\"alies\":\"string\",\"createTime\":\"2019-06-13T03:34:59.371Z\",\"delFlag\":0,\"discount\":0,\"garageId\":0,\"id\":0,\"orgId\":0,\"priceType\":0,\"updateTime\":\"2019-06-13T03:34:59.371Z\"}],\"passengerRuleInfo\":{\"aliasId\":0,\"ccs5\":\"string\",\"createTime\":\"2019-06-13T03:34:59.371Z\",\"discount\":0,\"garageId\":0,\"id\":0,\"orgId\":0,\"price\":0,\"priceWorkhourId\":0,\"totalPrice\":0,\"updateTime\":\"2019-06-13T03:34:59.371Z\",\"vecMod\":\"string\"},\"workhourRuleVOs\":[{\"aliasId\":0,\"ccs5\":\"string\",\"createTime\":\"2019-06-13T03:34:59.371Z\",\"discount\":0,\"garageId\":0,\"id\":0,\"orgId\":0,\"price\":0,\"priceWorkhourId\":0,\"totalPrice\":0,\"updateTime\":\"2019-06-13T03:34:59.371Z\",\"vecMod\":\"string\"}]}";
//        System.out.println("key: " + json);
//        String keySecurity = Base64.byteArrayToBase64(RSAUtils.encryptByPublicKey(json.getBytes()));
//        System.out.println("keySecurity: " + keySecurity);
//        String keydecode = new String(RSAUtils.decryptByPrivateKey(Base64.base64ToByteArray(keySecurity)));
//        System.out.println("keydecode: " + keydecode);
//    }

}

