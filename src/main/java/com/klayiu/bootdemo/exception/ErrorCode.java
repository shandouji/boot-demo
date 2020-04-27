package com.klayiu.bootdemo.exception;

import org.springframework.http.HttpStatus;

/**
 * @author 刘凯
 * @create 2020-04-11 9:23
 *
 *
 * 异常状态码 (枚举类), 引入 Spring HttpStatus
 */
public enum ErrorCode {


    RESOURCE_NOT_FOUND(404, HttpStatus.NOT_FOUND, "未找到该资源"),


    INTERNAL_SERVER_ERROR(500, HttpStatus.INTERNAL_SERVER_ERROR, "服务器异常");


    private final int code;

    private final HttpStatus status;

    private final String message;


    ErrorCode(int code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }


    @Override
    public String toString() {
        return "ErrorCode{" +
                "code=" + code +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
