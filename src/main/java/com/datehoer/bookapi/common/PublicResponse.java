package com.datehoer.bookapi.common;

import lombok.Data;

@Data
public class PublicResponse<T> {
    private Integer code;
    private String msg;
    private T data;

    // 默认成功响应
    public static <T> PublicResponse<T> success(T object) {
        return success(1, "success", object);
    }

    // 指定消息的成功响应
    public static <T> PublicResponse<T> success(String msg, T object) {
        return success(1, msg, object);
    }

    // 指定代码和消息的成功响应
    public static <T> PublicResponse<T> success(Integer code, String msg, T object) {
        PublicResponse<T> publicResponse = new PublicResponse<>();
        publicResponse.code = code;
        publicResponse.msg = msg;
        publicResponse.data = object;
        return publicResponse;
    }

    // 默认错误响应
    public static <T> PublicResponse<T> error(T object) {
        return error(0, "error", object);
    }

    // 指定消息的错误响应
    public static <T> PublicResponse<T> error(String msg, T object) {
        return error(0, msg, object);
    }

    // 指定代码和消息的错误响应
    public static <T> PublicResponse<T> error(Integer code, String msg, T object) {
        PublicResponse<T> publicResponse = new PublicResponse<>();
        publicResponse.code = code;
        publicResponse.msg = msg;
        publicResponse.data = object;
        return publicResponse;
    }
}