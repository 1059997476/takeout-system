package com.nchu.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Result<T> {
    private T data;
    private String msg;
    private int code;

    public Result(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }
}
