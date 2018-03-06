package com.baby.common.exceptions;

/**
 * Created by Administrator on 2017/9/26.
 */
public class BaseException extends RuntimeException{


    /**
     * 异常信息
     */
    protected String msg;

    /**
     * 具体异常码
     */
    protected int code;

    public BaseException() {
        super();
    }

    public BaseException(int code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

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
}