package com.myBabyTao.api.enums;

/**
 * Author:曾庆根
 * Created in 15:43 2017/12/15.
 */
public enum WechatMessgeTypeEnum {
    TEXT("text",1),
    //图片消息
    IMAGE("image",2),
    //语音消息
    VOICE("voice",3),
    //视频消息
    VIDEO("video",4),
    //图文消息（点击跳转到外链）
    NEWS("news",5),
    //图文消息（点击跳转到图文消息页面）
    MPNEWS("mpnews",6),
    //卡券消息
    WXCARD("wxcard",7),
    //音乐消息
    MUSIC("music",8),
    //发送小程序消息
    MINIPROGRAMPAGE("miniprogrampage",9),
    //模板消息
    TEMPLATE("template",10);

    /**描述*/
    private String desc;

    /**枚举值*/
    private int value;

    private WechatMessgeTypeEnum(String desc, int value){
        this.desc = desc;
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static WechatMessgeTypeEnum from(String status) {
        for (WechatMessgeTypeEnum wechatMessgeTypeEnum : WechatMessgeTypeEnum.values()) {
            if (wechatMessgeTypeEnum.getDesc().equals(status)) {
                return wechatMessgeTypeEnum;
            }
        }
        return null;
    }
}
