package com.cck.common.result;

import com.cck.common.enums.ResultCode;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by AAS on 2018/3/20.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    private  int code;

    private  String message;
    private  String detail;

    private  T data;



    public Result() {
    }

    public Result(ResultCode resultCode, T data) {
        this(resultCode);
        this.data = data;
    }

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMsg();
    }


    public Result  fail(int status,String msg,String detail){
        this.code = status;
        this.message = msg;
        this.detail = detail;
        return  this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
