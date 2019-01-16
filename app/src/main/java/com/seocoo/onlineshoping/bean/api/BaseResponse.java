package com.seocoo.onlineshoping.bean.api;

/**
 * desc: 接口响应类
 *
 * @author Bian
 * create at 2018/12/17 15:34
 */
public class BaseResponse<T> {
    private String msg;
    private int code;
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
}
