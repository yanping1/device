package com.lensyn.device.common.response;

import com.lensyn.device.common.exception.BusinessException;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * 统一返回结果处理
 * 配置包路径可避免类似/actuator/*路径被处理
 *
 * @author : gumingjun
 * @date: 2020-02-20 10:22
 */
@RestControllerAdvice("com.lensyn")
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return Objects.requireNonNull(returnType.getMethod()).getReturnType() != Response.class;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof Response) {
            return body;
        }
        if (body instanceof String) {
            //字符串返回可使用JSON处理
            //"return new Gson().toJson(Response.success(body));"
            //"return JSON.toJSONString(Response.success(body));"
            throw new BusinessException(ResultCode.SYS_RETURN_NOT_SUPPORT_STRING);
        }
        return Response.success(body);
    }
}
