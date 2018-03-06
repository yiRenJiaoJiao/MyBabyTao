package com.baby.common.enums;

/**
 * Created by xcy on 2017-10-11.
 */
public enum ActionTypeEnum {
    ACTION_TYPE_OPEN("打开",10),
    ACTION_TYPE_CLICK("点击",20),
    ACTION_TYPE_UNSUBSCRIBE("退订",30),
    ACTION_TYPE_HARD_BOUNCE("硬退",40),
    ACTION_TYPE_SOFT_BOUNCE("软退",50),
    ACTION_TYPE_SHARE("分享",60);

    /**描述*/
    private String desc;

    /**枚举值*/
    private Integer value;

    private ActionTypeEnum(String desc, Integer value){
        this.desc = desc;
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
