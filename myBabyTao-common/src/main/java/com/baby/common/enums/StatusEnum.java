package com.baby.common.enums;

/**
 * Created by SEELE on 2017/8/10.
 */
public enum StatusEnum {

    STATUS_NORNAL("正常",0),
    STATUS_DEL("删除",-1);
    /**描述*/
    private String desc;

    /**枚举值*/
    private Integer value;

    private StatusEnum(String desc, Integer value){
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
