package com.baby.common.service;

/**
 * Created by SEELE on 2017/8/7.
 */
public interface UserBaseService<M> {

        Integer insert(M m, Integer userId);
        Integer updateByPrimaryKey(M m, Integer userId);
        Integer deleteByPrimaryKey(Integer id, Integer userId);
        M selectByPrimaryKey(Integer id, Integer userId);
        int insertSelective(M m, Integer userId);
        int updateByPrimaryKeySelective(M m, Integer userId);



}
