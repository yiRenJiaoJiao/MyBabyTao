package com.baby.common.entity;

/**
 * Created by LJL on 2017/9/6.
 * 这个类用于存储联系人的基础属性
 * 表中字段名,类中属性名,字段类型,字段长度
 */
public class BaseField {

    private String tableFieldName; //数据表中的列名
    private String classFieldName; //程序属性名
    private String fieldType;     //字段类型
    private Integer fieldLength;  //字段长度
    private boolean isSystem;     //是否为系统字段，true为是
    private String chineseName; //显示字段名称


    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public boolean isSystem() {
        return isSystem;
    }

    public void setIsSystem(boolean isSystem) {
        this.isSystem = isSystem;
    }

    public String getTableFieldName() {
        return tableFieldName;
    }

    public void setTableFieldName(String tableFieldName) {
        this.tableFieldName = tableFieldName;
    }

    public String getClassFieldName() {
        return classFieldName;
    }

    public void setClassFieldName(String classFieldName) {
        this.classFieldName = classFieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public Integer getFieldLength() {
        return fieldLength;
    }

    public void setFieldLength(Integer fieldLength) {
        this.fieldLength = fieldLength;
    }
}
