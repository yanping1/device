package com.lensyn.device.common.exception;

import com.lensyn.device.common.response.IResultCodeEnum;
import org.springframework.http.HttpStatus;

/**
 * 自定义业务异常
 *
 * @author : gumingjun
 * @date: 2020-02-20 10:22
 */
public class BusinessException extends RuntimeException {
    private int code;

    public BusinessException(IResultCodeEnum<Integer, String> resultCode) {
        super(resultCode.getDesc());
        this.code = resultCode.getCode();
    }

    public BusinessException(String msg) {
        super(msg);
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    public BusinessException(String msg, Throwable cause) {
        super(msg, cause);
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
