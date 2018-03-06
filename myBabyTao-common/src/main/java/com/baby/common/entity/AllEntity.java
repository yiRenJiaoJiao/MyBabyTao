package com.baby.common.entity;

import java.io.Serializable;
import java.util.TreeMap;

/**
 * 超级类
 * @author Administrator
 *
 */
public class AllEntity extends TreeMap<Object, Object> implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public AllEntity setProperties(String str , Object object){
        this.put(str, object);
        return this;

    }


}