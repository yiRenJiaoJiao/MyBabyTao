package com.baby.common.enums;

/**
 * Author:曾庆根
 * Created in 9:32 2017/9/22.
 */
public enum DomainStatusEnum {
    STATUS_AVAILABLE("可用",0),
    STATUS_UNAVAILABLE("不可用",1),
    STATUS_DEL("删除",-1);
    /**描述*/
    private String desc;

    /**枚举值*/
    private Integer value;

    private DomainStatusEnum(String desc, Integer value){
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
