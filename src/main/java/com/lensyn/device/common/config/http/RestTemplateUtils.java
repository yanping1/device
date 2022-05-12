package com.lensyn.device.common.config.http;

import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @ProjectName: device
 * @ClassName: RestTemplateUtils
 * @Description:
 * @Author yanping
 * @Date 2022-05-12 11:14
 */
public class RestTemplateUtils {
//
//    /**
//     * GET请求调用方式
//     *
//     * @param url 请求URL
//     * @param responseType 返回对象类型
//     * @param uriVariables URL中的变量，与Map中的key对应
//     * @return ResponseEntity 响应对象封装类
//     */
//    public  <T> ResponseEntity<T> get(String url, Class<T> responseType, Map<String, ?> uriVariables) {
//        return restTemplate.getForEntity(url, responseType, uriVariables);
//    }
//
//    /**
//     * 带请求头的GET请求调用方式
//     *
//     * @param url 请求URL
//     * @param headers 请求头参数
//     * @param responseType 返回对象类型
//     * @param uriVariables URL中的变量，按顺序依次对应
//     * @return ResponseEntity 响应对象封装类
//     */
//    public  <T> ResponseEntity<T> get(String url, HttpHeaders headers, Class<T> responseType, Object... uriVariables) {
//        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
//        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseType, uriVariables);
//    }
//
//    /**
//     * 带请求头的GET请求调用方式
//     *
//     * @param url 请求URL
//     * @param headers 请求头参数
//     * @param responseType 返回对象类型
//     * @param uriVariables URL中的变量，与Map中的key对应
//     * @return ResponseEntity 响应对象封装类
//     */
//    public  <T> ResponseEntity<T> get(String url, Map<String, String> headers, Class<T> responseType, Map<String, ?> uriVariables) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setAll(headers);
//        return get(url, httpHeaders, responseType, uriVariables);
//    }


    private static final String EMPTY_STR = "";

    /**
     * get请求
     *
     * @param restTemplate
     * @param url
     * @param headerMap
     * @param paramMap
     * @return
     */
    public static String get(RestTemplate restTemplate, String url, Map<String, String> headerMap, Map<String, String> paramMap) {
        HttpHeaders headers = new HttpHeaders();
        if (!CollectionUtils.isEmpty(headerMap)) {
            headerMap.forEach((k, v) -> headers.set(k, v));
        }
        StringBuffer paramStr = new StringBuffer(EMPTY_STR);
        if (!CollectionUtils.isEmpty(paramMap)) {
            paramMap.forEach((k, v) -> {
                if (paramStr.toString().equals(EMPTY_STR)) {
                    paramStr.append("?").append(k).append("=").append(v);
                } else {
                    paramStr.append("&").append(k).append("=").append(v);
                }
            });
        }
        HttpEntity<String> httpEntity = restTemplate.exchange(url + paramStr.toString(), HttpMethod.GET, CollectionUtils.isEmpty(headerMap) ? null : new HttpEntity<>(headers), String.class);
        return httpEntity.getBody();
    }

    /**
     * post JSON
     *
     * @param restTemplate
     * @param url
     * @param headerMap
     * @param paramObjectStr
     * @return
     */
    public static String postJson(RestTemplate restTemplate, String url, Map<String, String> headerMap, String paramObjectStr) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        if (!CollectionUtils.isEmpty(headerMap)) {
            headerMap.forEach((k, v) -> headers.set(k, v));
        }
        String resultStr = restTemplate.postForObject(url, new HttpEntity<>(paramObjectStr, headers), String.class);
        return resultStr;
    }

    /**
     * post Form
     *
     * @param restTemplate
     * @param url
     * @param headerMap
     * @param paramMap
     * @return
     */
    public static String postForm(RestTemplate restTemplate, String url, Map<String, String> headerMap, MultiValueMap<String, Object> paramMap) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        if (!CollectionUtils.isEmpty(headerMap)) {
            headerMap.forEach((k, v) -> headers.set(k, v));
        }
        String resultStr = restTemplate.postForObject(url, new HttpEntity<>(paramMap, headers), String.class);
        return resultStr;
    }


}
