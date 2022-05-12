package com.lensyn.device.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : gumingjun
 * @date: 2020-02-20 09:43
 */
@Data
@NoArgsConstructor
public class Response<T> implements Serializable {
    private Integer code;
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public Response(IResultCodeEnum<Integer, String> resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getDesc();
    }

    public Response(IResultCodeEnum<Integer, String> resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getDesc();
        this.data = data;
    }

    public Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Response success() {
        return new Response<>(ResultCode.SUCCESS);
    }

    public static <T> Response success(T data) {
        return new Response<>(ResultCode.SUCCESS, data);
    }

    public static <T> Response success(String msg) {
        return new Response<>(ResultCode.SUCCESS.getCode(), msg);
    }


    public static <T> Response success(int code, String msg) {
        return new Response<>(code, msg);
    }

    public static <T> Response success(int code, String msg, T data) {
        return new Response<>(code, msg, data);
    }

    public static Response failure() {
        return new Response<>(ResultCode.FAILURE);
    }

    public static Response failure(String msg) {
        return new Response<>(ResultCode.FAILURE.getCode(), msg);
    }

    public static Response failure(IResultCodeEnum<Integer, String> resultCode) {
        return new Response<>(resultCode);
    }

    public static <T> Response failure(int code, String msg) {
        return new Response<>(code, msg);
    }
}
