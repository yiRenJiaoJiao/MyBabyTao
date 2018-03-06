package com.baby.common.enums;

/**
 * Created by LJL on 2017/9/1.
 *
 * 自定义字段的
 */
public enum FieldTypeEnum {
    FIELD_TYPE_INT("数字","int"),
    FIELD_TYPE_STRING("文本","varchar"),
    FIELD_TYPE_DATE("日期","datetime");

    private String desc;    //描述
    private String value;   //内容

    private FieldTypeEnum(String desc,String value){
        this.desc = desc;
        this.value = value;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
