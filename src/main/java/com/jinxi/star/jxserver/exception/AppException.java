package com.jinxi.star.jxserver.exception;

public class AppException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private int code;

    public AppException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
