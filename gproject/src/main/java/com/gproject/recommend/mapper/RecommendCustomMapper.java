package com.gproject.recommend.mapper;

import com.gproject.recommend.pojo.RecommendCustom;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */
public interface RecommendCustomMapper {

    public void insertRecommendProduct(RecommendCustom custom);

    public RecommendCustom selectByUserIdAndCategoryId(RecommendCustom custom);

    public List<Integer> selectCategoryIdByUserId(Integer userId);

    public void updateByPrimaryKeySelective(RecommendCustom custom);

}
