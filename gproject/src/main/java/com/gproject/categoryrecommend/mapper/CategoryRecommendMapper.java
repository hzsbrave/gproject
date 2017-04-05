package com.gproject.categoryrecommend.mapper;

import com.gproject.categoryrecommend.pojo.CategoryRecommend;

public interface CategoryRecommendMapper {
    int deleteByPrimaryKey(Integer caterecomId);

    int insert(CategoryRecommend record);

    int insertSelective(CategoryRecommend record);

    CategoryRecommend selectByPrimaryKey(Integer caterecomId);

    int updateByPrimaryKeySelective(CategoryRecommend record);

    int updateByPrimaryKey(CategoryRecommend record);
}