package com.baby.common.service;

/**
 * Created by SEELE on 2017/8/7.
 */
public interface BaseService<M> {

        Integer insert(M m);
        Integer updateByPrimaryKey(M m);
        Integer deleteByPrimaryKey(Integer id);
        M selectByPrimaryKey(Integer id);
        int insertSelective(M m);
        int updateByPrimaryKeySelective(M m);
}
