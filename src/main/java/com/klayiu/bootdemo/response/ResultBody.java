package com.klayiu.bootdemo.response;

import com.klayiu.bootdemo.exception.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

/**
 * @author 刘凯
 * @create 2020-04-13 16:06
 *
 *  前后端统一消息定义数据状态码，便于数据交互
 *
 *  例子：
 * {
 *     "code":"0",
 *     "messgae":"操作成功",
 *     "data":{
 *         "id":1,
 *         "userName":"张三",
 *         "passWord":"123",
 *         "email":"张三@dakai163.com",
 *         "sex":"男",
 *         "status":"1",
 *         "telePhone":"13004063306"
 *     }
 * }
 *
 */
public class ResultBody<T> {


    private String code;
    private String messgae;
    private T data;


    /**
     *若没有数据返回，默认状态码为0，提示信息为：操作成功！
     */
    public ResultBody(){
        this.code = "0";
        this.messgae = "操作成功 !";
    }


    /**
     * 若没有数据返回, 可指定状态码和提示信息
     * @param result
     * @param messgae
     */
    public ResultBody(String result, String messgae){
        this.code = code;
        this.messgae = messgae;
    }

    /**
     * 有数据返回时，状态码为0,默认提示信息为: 操作成功 ！
     * @param data
     */
    public ResultBody(T data){
        this.data = data;
        this.code = "0";
        this.messgae = "操作成功";
    }


    /**
     * 有数据返回,状态码为0,可指定提示信息
     * @param data
     * @param messgae
     */
    public ResultBody(T data, String messgae) {
        this.data = data;
        this.code = "0";
        this.messgae = messgae;
    }

    public ResultBody(ErrorResponse reponse, HttpHeaders httpHeaders, HttpStatus status) {
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessgae() {
        return messgae;
    }

    public void setMessgae(String messgae) {
        this.messgae = messgae;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
