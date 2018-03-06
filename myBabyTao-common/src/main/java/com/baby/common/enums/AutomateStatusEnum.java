package com.baby.common.enums;

/**
 * Created by ljl on 2017/9/22.
 */
public enum AutomateStatusEnum {

    AUTOMATE_STATUS_START("启用",10),
    AUTOMATE_STATUS_UNSTART("未启用",20),
    AUTOMATE_STATUS_DELETE("删除",-1);


    private String desc;    //描述
    private Integer value;   //内容

    private AutomateStatusEnum(String desc,Integer value){
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
