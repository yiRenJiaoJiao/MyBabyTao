package com.myBabyTao.api.service;

import javax.servlet.http.HttpServletRequest;

import java.util.*;
/**
 * Created by Administrator on 2018/1/25.
 */
public interface WechatService {

    String getAccessToken();

    /**
     * 接收用户消息
     * @param map
     * @return
     */
    String processRequest(Map<String ,String> map);

    String sendWechatServerMessage(String messageType, String openId, String content, String mediaId, String wechatCountOpenId, Integer userId);

    /**
     * 获取公众号的
     * @return
     */
    boolean getWechatFansInfo();


}
