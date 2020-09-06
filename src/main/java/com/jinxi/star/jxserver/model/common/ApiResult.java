package com.jinxi.star.jxserver.model.common;

import java.io.Serializable;

public class ApiResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static ApiResult fail(int code, String errmsg) {
        ApiResult result = new ApiResult();
        result.setCode(code);
        result.setMsg(errmsg);
        return result;
    }

    public static ApiResult succ() {
        ApiResult result = new ApiResult();
        result.setCode(0);
        result.setMsg("成功");
        return result;
    }
}