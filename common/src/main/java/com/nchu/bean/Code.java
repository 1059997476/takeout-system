package com.nchu.bean;

public enum Code {
    success(200);

    private int code;

    Code(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
