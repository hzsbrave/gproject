package com.gproject.category.facade;

import com.gproject.category.pojo.Category;
import com.gproject.category.pojo.vo.CategoryCondition;

import java.util.List;

/**
 * CategoryFacade.java
 * describe: 分类业务接口
 * Author hezishan
 * Date 2017/2/23 9:52
 */
public interface CategoryFacade {

    /**
     *查询指定条数的主分类
     * @return
     */
   Object queryHotCateIndex(Integer condition);
    /**
     *查询指定条数的主分类
     * @return
     */
    Object querySecondAndThirdCat(CategoryCondition condition);
}
