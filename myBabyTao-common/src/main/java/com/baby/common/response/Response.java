package com.baby.common.response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务器返回手机端数据格式
 */
public class Response implements Serializable{/**
 * 错误代码
 */
private int code;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 数据
     */
    private Map<String, Object> data;

    public Response(int code, String message, boolean success,
                    Map<String, Object> data) {
        super();
        this.code = code;
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public static Response addFailedResponse(String message,Map<String, Object> data){
        return new Response(0, message, false, data);
    }

    public static Response addFailedResponse(String message){
        return new Response(0, message, false, null);
    }

    public static Response addSuccessResponse(String message,Map<String, Object> data){
        return new Response(1, message, true, data);
    }

    public static Response addListResponse(String message,int total,List<?> list){
        Map<String, Object>	data = new HashMap<String, Object>();
        data.put("total", total);
        data.put("list", list);
        return new Response(1, message, true, data);
    }
}
