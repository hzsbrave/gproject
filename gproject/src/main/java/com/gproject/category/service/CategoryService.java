package com.gproject.category.service;

import com.gproject.base.mapper.BaseMapper;
import com.gproject.base.service.BaseService;
import com.gproject.category.facade.CategoryFacade;
import com.gproject.category.mapper.CategoryMapper;
import com.gproject.category.mapper.CategoryMapperCustom;
import com.gproject.category.pojo.Category;
import com.gproject.category.pojo.CategoryCustom;
import com.gproject.category.pojo.vo.CategoryCondition;
import com.gproject.category.pojo.vo.CategoryResponseVo;
import com.gproject.util.message.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CategoryService.java
 * describe: 分类业务实现类
 * Author hezishan
 * Date 2017/2/23 9:55
 */
@Service
public class CategoryService extends BaseService<Category,Integer> implements CategoryFacade {

    @Autowired
    private CategoryMapperCustom categoryMapperCustom;

    @Override
    public Object queryHotCateIndex(Integer condition) {
        if(null==condition){
            condition=8;
        }
        List<CategoryCustom> categories=categoryMapperCustom.queryHotCateIndex(condition);
        return SUCCESS(categories);
    }

    @Override
    public Object querySecondAndThirdCat(CategoryCondition condition) {
        if(null==condition)
            return FAIL(ResponseType.PARAMETER_NULL,"query condition is null");
        List<CategoryResponseVo> responseVoList=categoryMapperCustom.querySecondAndThirdCat(condition);
        return SUCCESS(responseVoList);
    }

    @Override
    protected BaseMapper<Category, Integer> getMapper() {
        return null;
    }

    @Override
    protected String getMapperNameSpace() {
        return null;
    }

    @Override
    protected String getDefalultDatabase() {
        return null;
    }


}
