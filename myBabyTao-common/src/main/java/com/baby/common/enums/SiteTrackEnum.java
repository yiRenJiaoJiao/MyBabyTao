package com.baby.common.enums;

/**
 * Created by LJL on 2017/10/12.
 *
 * 站点跟踪的操作类型枚举
 */
public enum SiteTrackEnum {
    TRACK_OPEN("打开",10),
    TRACK_CLOSE("关闭",40),
    TRACK_CLICK("点击",20),
    TRACK_EVENT("事件",30);


    private String desc;    //描述
    private Integer value;   //内容

    private SiteTrackEnum(String desc,Integer value){
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
