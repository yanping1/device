package com.lensyn.device.common.exception;

import com.lensyn.device.common.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.unit.DataSize;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 统一异常处理
 *
 * @author gumingjun on 2018/11/15.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @Value("${spring.servlet.multipart.max-file-size:}")
    private DataSize maxFileSize;
    @Value("${spring.servlet.multipart.max-request-size:}")
    private DataSize maxRequestSize;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response exceptionErrorHandler(Exception e) {
        log.error("exceptionErrorHandler:系统异常", e);
        return Response.failure();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response illegalArgumentExceptionErrorHandler(IllegalArgumentException e) {
        log.error("illegalArgumentExceptionErrorHandler:参数异常", e);
        return Response.failure(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response businessExceptionErrorHandler(BusinessException e) {
        log.error("businessExceptionErrorHandler:系统异常", e);
        return Response.failure(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response accessDeniedExceptionHandler(AccessDeniedException e) {
        log.error("accessDeniedExceptionHandler:访问权限异常", e);
        return Response.failure(HttpStatus.BAD_REQUEST.value(), "您没有访问权限!");
    }

    /**
     * Post body 请求参数不正确
     *
     * @param e 请求参数不正确
     * @return 统一异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        log.error("httpMessageNotReadableException:系统异常", e);
        return Response.failure(HttpStatus.BAD_REQUEST.value(), "请求参数错误!");
    }

    /**
     * 请求参数异常
     *
     * @param e 请求参数异常
     * @return 异常信息
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        log.error("missingServletRequestParameterExceptionHandler:参数异常", e);
        return Response.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(), "请求参数错误!");
    }

    /**
     * 文件上传大小限制
     *
     * @param e 文件上传大小限制异常
     * @return 异常信息
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response maxUploadSizeExceededExceptionExceptionHandler(MaxUploadSizeExceededException e) {
        log.error("maxUploadSizeExceededExceptionExceptionHandler:文件上传大小限制异常", e);
        return Response.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(), String.format("文件大小超出限制：单个文件不超过%sM，多个文件不超过%sM!",
                maxFileSize.toMegabytes(), maxRequestSize.toMegabytes()));
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Response httpRequestMethodNotSupportedExceptionErrorHandler(HttpRequestMethodNotSupportedException e) {
        log.error("httpRequestMethodNotSupportedExceptionErrorHandler:不支持当前请求方法异常", e);
        return Response.failure(HttpStatus.METHOD_NOT_ALLOWED.value(), "不支持当前请求方法");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({SQLException.class})
    public Response sqlExceptionErrorHandler(SQLException e) {
        log.error("sqlExceptionErrorHandler:SQL异常", e);
        return Response.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(), "SQL异常!");
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response bindExceptionErrorHandler(BindException e) {
        log.debug("bindExceptionErrorHandler:参数校验异常", e);
        List<FieldError> fieldError = e.getFieldErrors();
        String errorMsg = getFieldErrorResolver(fieldError);
        return Response.failure(HttpStatus.BAD_REQUEST.value(), errorMsg);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response validationExceptionHandler(ValidationException e) {
        log.debug("validationExceptionHandler:参数校验异常", e);
        return Response.failure(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response methodArgumentNotValidExceptionErrorHandler(MethodArgumentNotValidException e) {
        log.debug("methodArgumentNotValidExceptionErrorHandler:参数校验异常", e);
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String errorMsg = getFieldErrorResolver(fieldErrors);
        return Response.failure(HttpStatus.BAD_REQUEST.value(), errorMsg);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response constraintViolationExceptionErrorHandler(ConstraintViolationException e) {
        log.debug("constraintViolationExceptionErrorHandler:参数校验异常", e);
        // List<String> errorMsgList = new ArrayList<>();
        // for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
        //     errorMsgList.add(constraintViolation.getMessage());
        // }
        final String msg = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                //多个异常信息进行格式处理
                .collect(Collectors.joining(";"));
        return Response.failure(HttpStatus.BAD_REQUEST.value(), msg);
    }

    /**
     * 属性错误解析
     *
     * @param fieldError 属性错误对象
     * @return 错误信息
     */
    private String getFieldErrorResolver(List<FieldError> fieldError) {
        // StringBuilder errorMsg = new StringBuilder(50);
        // errorMsg.append("[");
        // for (FieldError error : fieldError) {
        //     errorMsg.append(error.getDefaultMessage()).append(",");
        // }
        // errorMsg.deleteCharAt(errorMsg.length() - 1);
        // errorMsg.append("]");
        // return errorMsg.toString();
        return fieldError.stream().map(FieldError::getDefaultMessage)
                //多个异常信息进行格式处理
                .collect(Collectors.joining(";"));
    }
}
