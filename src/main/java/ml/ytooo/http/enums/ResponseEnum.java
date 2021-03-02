package ml.ytooo.http.enums;

public enum ResponseEnum {


    SUCCESS("0", "成功"),
    FAILED("-1", "失败"),

    /**
     * 数据校验失败
     */
    VALID_FAILED("1001", "校验失败"),

    /**
     * 数据查询失败
     */
    QUERY_FAILED("1002", "查询失败"),

    /**
     * /**
     * 数据更新失败
     */
    MODIFY_FAILED("1003", "更新失败"),
    /**
     * 数据新增失败
     */
    INSERT_FAILED("1004", "新增失败"),
    /**
     * 数据删除失败
     */
    DELETE_FAILED("1005", "删除失败"),
    /**
     * 数据导出失败
     */
    EXPORT_FAILED("1006", "导出失败"),
    OPERATE_FAILED("1007", "操作失败"),
    LOGIN_FAILD("1008", "登录失败"),
    UPLOAD_FAILED("1009", "上传失败"),
    SUBMIT_FAILD("1010", "提交失败"),

    /**
     * 请求Token缺失
     */
    TOKEN_UNAVAILABLE("2000", "请求Token缺失，请提供Token！"),
    /**
     * Token过期
     */
    TOKEN_EXPIRE("2001", "Token过期，请重新申请"),
    /**
     * Token对应的用户丢失
     */
    TOKEN_LOST("2002", "Token对应的用户丢失"),
    /**
     * 该用户没有权限
     */
    NOT_AUTHORIZED("2003", "该用户没有权限");

    /**
     * 编码
     */
    private String code;

    /**
     * 值
     */
    private String message;

    private ResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
