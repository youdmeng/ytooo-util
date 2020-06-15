package ml.ytooo.security.bean;

/**
 * VerifyCode
 */
public class VerifyCode {
    /**
     * code
     */
    private String code;

    private String base64;

    /**
     * imgBytes
     */
    private byte[] imgBytes;

    /**
     * expireTime
     */
    private long expireTime;

    /**
     * 属性get
     */
    public String getBase64() {
        return base64;
    }

    /**
     * 属性set
     */
    public void setBase64(String base64) {
        this.base64 = base64;
    }

    /**
     * 属性get
     */
    public String getCode() {
        return code;
    }

    /**
     * 属性set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 属性get
     */
    public byte[] getImgBytes() {
        return imgBytes;
    }

    /**
     * 属性set
     */
    public void setImgBytes(byte[] imgBytes) {
        this.imgBytes = imgBytes;
    }

    /**
     * 属性get
     */
    public long getExpireTime() {
        return expireTime;
    }

    /**
     * 属性set
     */
    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }
}
