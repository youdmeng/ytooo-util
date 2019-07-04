package com.ytooo.http;

public class Result {
    public Result() {}

    public static Response success() { /* compiled code */
        return new Response();
    }

    public static <T> Response<T> success(String message, T t) {
        return new Response<T>(message, t);
    }

    public static <T> Response<T> success(int code, String message, T t) {
        return new Response<T>(code, message, t);
    }

    public static <T> Response<T> success(T t) {
        return new Response<T>(t);
    }

    public static Response error() {
        return new Response(-1, "error");
    }

    public static Response error(String message) {
        return new Response(-1, message);
    }

    public static Response error(int code, String message) {
        return new Response(code, message);
    }

    public static <T> Response<T> error(int code, String message, T t) {
        return new Response<T>(code, message, t);
    }
}