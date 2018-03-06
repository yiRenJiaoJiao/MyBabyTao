package com.baby.common.BaseMapper;

public interface BaseMapper<M> {

    Integer insert(M m);
    Integer updateByPrimaryKey(M m);
    Integer deleteByPrimaryKey(Integer id);
    M selectByPrimaryKey(Integer id);
    int insertSelective(M m);
    int updateByPrimaryKeySelective(M m);
}
