package com.gproject.popular.mapper;

import com.gproject.popular.pojo.Popular;

public interface PopularMapper {
    int deleteByPrimaryKey(Integer popularId);

    int insert(Popular record);

    int insertSelective(Popular record);

    Popular selectByPrimaryKey(Integer popularId);

    int updateByPrimaryKeySelective(Popular record);

    int updateByPrimaryKey(Popular record);
}