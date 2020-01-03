package com.ytooo.http;

public class Result {
    public Result() {
    }

    public static <T> Response<T> success() { /* compiled code */
        return new Response<T>();
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

    public static <T> Response<T> error() {
        return new Response<T>(-1, "error");
    }

    public static <T> Response<T> error(String message) {
        return new Response<T>(-1, message);
    }

    public static <T> Response<T> error(int code, String message) {
        return new Response<T>(code, message);
    }

    public static <T> Response<T> error(int code, String message, T t) {
        return new Response<T>(code, message, t);
    }
}