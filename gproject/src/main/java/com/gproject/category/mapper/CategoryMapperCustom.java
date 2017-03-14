package com.gproject.category.mapper;

import com.gproject.category.pojo.CategoryCustom;
import com.gproject.category.pojo.vo.CategoryCondition;
import com.gproject.category.pojo.vo.CategoryExample;
import com.gproject.category.pojo.vo.CategoryResponseVo;

import java.util.List;

/**
 * CategoryMapperCustom.java
 * describe: 分类拓展类Mapper
 * Author hezishan
 * Date 2017/2/23 9:43
 */
public interface CategoryMapperCustom {

    List<CategoryCustom> queryHotCateIndex(Integer condition);

    List<CategoryResponseVo> querySecondAndThirdCat(CategoryCondition condition);

    CategoryCustom selectCategoryById(CategoryExample condition);

}