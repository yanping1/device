package com.lensyn.device.common.response;

import org.springframework.http.HttpStatus;

/**
 * @author : gumingjun
 * @date: 2020-02-20 09:37
 */
public enum ResultCode implements IResultCodeEnum<Integer, String> {
    /*默认使用HTTP规定状态码*/
    SUCCESS(HttpStatus.OK.value(), "成功!"), FAILURE(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器内部错误!"),

    /*业务代码*/
    APPLICATION_WX_UNBIND(1002, "微信与系统用户未绑定"),

    /*系统限定：2001-2999*/
    SYS_RETURN_NOT_SUPPORT_STRING(2001, "返回数据不支持字符串类型,请使用对象封装，便于JSON解析处理!"),
    SYS_FEIGN_CALL_ERROR(2002, "feign调用错误!");




    /**
     * 状态码
     */
    private final Integer code;
    /**
     * 状态描述
     */
    private final String desc;

    ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
