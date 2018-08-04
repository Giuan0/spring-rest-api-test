package com.example.models.response;

import java.util.List;

public class DefaultResponse<T>{
    private String message;
    private int code;
    private T data;

    public DefaultResponse(T data, String message, int code){
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public DefaultResponse(){}

    public T getData() {
        return this.data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return this.message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }
    public void setCode(int code) {
        this.code = code;
    }



}