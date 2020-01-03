package com.ytooo.http;

public class Response<T> {

    private int code = 0;

    private String msg = "success";

    private T data;

    Response() {
    }

    Response(T data) {
        this.data = data;
    }

    Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(int code, T data) {
        this.code = code;
        this.data = data;
    }

    Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    Response(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    public Response(String msg) {
        this.msg = msg;
    }

    /**
     * 属性get
     */
    public int getCode() {
        return code;
    }

    /**
     * 属性set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 属性get
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 属性set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 属性get
     */
    public T getData() {
        return data;
    }

    /**
     * 属性set
     */
    public void setData(T data) {
        this.data = data;
    }
}