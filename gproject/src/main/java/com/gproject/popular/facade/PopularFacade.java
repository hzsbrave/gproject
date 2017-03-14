package com.gproject.popular.facade;

import com.gproject.popular.pojo.vo.PopularVo;

/**
 * Created by Administrator on 2017/3/3.
 */
public interface PopularFacade {
    /**
     * 查询热搜的前几条数据
     * @param vo
     * @return
     */
    public Object queryPopularLimit(PopularVo vo);
}
