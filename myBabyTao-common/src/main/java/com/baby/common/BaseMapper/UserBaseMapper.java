package com.baby.common.BaseMapper;

import org.apache.ibatis.annotations.Param;

public interface UserBaseMapper<M> {

    Integer insert(@Param("m") M m, @Param("userId") Integer userId);
    Integer updateByPrimaryKey(@Param("m") M m, @Param("userId") Integer userId);
    Integer deleteByPrimaryKey(@Param("id") Integer id, @Param("userId") Integer userId);
    M selectByPrimaryKey(@Param("id") Integer id, @Param("userId") Integer userId);
    int insertSelective(@Param("m") M m, @Param("userId") Integer userId);
    int updateByPrimaryKeySelective(@Param("m") M m, @Param("userId") Integer userId);
}
