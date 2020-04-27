package com.klayiu.bootdemo.exception;

import org.apache.commons.lang3.ObjectUtils;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘凯
 * @create 2020-04-11 9:23
 *
 * 作为异常信息返回客户端
 */
public class ErrorResponse {


    private int code;

    private int stauts;

    private String message;

    private String path;

    private Instant timestamp;

    private HashMap<String, Object> data = new HashMap<String, Object>();


    public ErrorResponse(){

    }

    public ErrorResponse(BaseErrorException ex, String path) {
        this(ex.getError().getCode(), ex.getError().getStatus().value(), ex.getError().getMessage(), path,ex.getData());
    }

    public ErrorResponse(int code, int stauts, String message, String path, Map<String, Object> data) {
        this.code = code;
        this.stauts = stauts;
        this.message = message;
        this.path = path;
        this.timestamp = Instant.now();
        if (!ObjectUtils.isEmpty(data)) {
            this.data.putAll(data);
        }
    }

    @Override
    public String toString() {
        return "ErrorReponse{" +
                "code=" + code +
                ", stauts=" + stauts +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                ", timestamp=" + timestamp +
                ", data=" + data +
                '}';
    }
}
