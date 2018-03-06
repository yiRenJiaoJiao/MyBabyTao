package com.baby.common.enums;

/**
 * Created by xcy on 2017-09-25.
 */
public enum  TaskTypeEnum {
    TASK_TYPE_ROUTINE("常规",10),
    TASK_TYPE_INTERFACE("接口",20),
    TASK_TYPE_AUTOMATION("自动化",30);

    /**描述*/
    private String desc;

    /**枚举值*/
    private Integer value;

    private TaskTypeEnum(String desc, Integer value){
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
