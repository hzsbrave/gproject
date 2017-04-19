package com.gproject.recommend.facade;

import com.gproject.recommend.pojo.RecommendCustom;

/**
 * Created by Administrator on 2017/3/31.
 */
public interface RecommendFacade {

    /**
     * 记录用户的个性化行为
     * @param custom
     * @return
     */
    public Object insertRecommenProduct(RecommendCustom custom);

}
