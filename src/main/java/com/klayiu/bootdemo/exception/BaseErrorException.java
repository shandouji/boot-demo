package com.klayiu.bootdemo.exception;

import org.apache.commons.lang3.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘凯
 * @create 2020-04-11 9:23
 *
 *
 * 封装自定义业务异常 继承RuntimeException
 */
public class BaseErrorException extends RuntimeException{



    private final ErrorCode error;

    private final HashMap<String, Object> data = new HashMap<>();

    public BaseErrorException(ErrorCode error, Map<String, Object> data) {
        super(error.getMessage());
        this.error = error;
        if (!ObjectUtils.isEmpty(data)) {
            this.data.putAll(data);
        }
    }

    protected BaseErrorException(ErrorCode error, Map<String, Object> data, Throwable cause) {
        super(error.getMessage(), cause);
        this.error = error;
        if (!ObjectUtils.isEmpty(data)) {
            this.data.putAll(data);
        }
    }

    public ErrorCode getError() {
        return error;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
