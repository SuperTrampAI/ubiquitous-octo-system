package com.github.supertrampai.util;

import com.fasterxml.jackson.annotation.JsonView;

public class Resp<T> {
    public interface RespView{}

    @JsonView(RespView.class)
    private int code;
    @JsonView(RespView.class)
    private String msg;
    @JsonView(RespView.class)
    private T data = null;

    private final static int SUCCESS = 0;

    public Resp(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Resp(){
        this.code = SUCCESS;
        this.msg = "SUCCESS";
    }

    public Resp(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Resp(T data){
        this.code = SUCCESS;
        this.msg = "SUCCESS";
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg(){return msg;}

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "{\"code\":" + code + ", \"msg\":\"" + msg + "\"}";
    }
}

