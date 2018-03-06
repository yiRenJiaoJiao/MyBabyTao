package com.baby.common.entity.wechat;

import com.baby.common.utils.wechat.WechatMessageUtil;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/26.
 */
public class TextMessage implements Serializable{
    private String MsgType;
    private String ToUserName;
    private String FromUserName;
    private long CreateTime;
    private String Content;
    private String MsgId;

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}
