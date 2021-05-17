package com.nchu.bean;

public enum Code {
    success(2000),
    error(2001),
    forbidden(403);

    private int code;

    Code(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
