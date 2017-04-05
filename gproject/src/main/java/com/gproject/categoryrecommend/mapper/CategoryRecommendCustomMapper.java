package com.gproject.categoryrecommend.mapper;

import com.gproject.categoryrecommend.pojo.CategoryRecommend;
import com.gproject.categoryrecommend.pojo.CategoryRecommendCustom;

import java.util.List;

public interface CategoryRecommendCustomMapper {

    public List<CategoryRecommendCustom> selectCategoryRecommendByNum(int num);

}