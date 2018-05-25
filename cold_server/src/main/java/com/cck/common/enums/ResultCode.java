package com.cck.common.enums;

/**
 * Created by AAS on 2018/3/20.
 */
public enum ResultCode {

    SUCCESS(200,"SUCCESS"),
    nodata(201,"暂无数据"),
    FAIL(500,"网络故障"),
    SAVEFAIL(500,"操作失败"),
    PASSWORD_ERROR(10001, "用户名或密码错误"),
    PARAMETER_ERROR(10101, "参数错误"),
    NO_TOKEN_ERROR(10102, "token无效");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
