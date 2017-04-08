package com.gproject.favorite.facade;

import com.gproject.favorite.pojo.FavoriteCustom;

/**
 * Created by Administrator on 2017/4/6.
 */
public interface FavoriteFacade {

    /**
     * 添加喜欢
     * 1.如果存在，则移除喜欢
     * 2.如果不存在，添加喜欢并提示添加成功
     * @param custom
     * @return
     */
    public Object insertFavorite(FavoriteCustom custom);

    /**
     * 根据用户id查询喜欢列表
     * @param userId
     * @return
     */
    public Object queryFavoriteByUserId(Integer userId);

    /**
     * 根据产品编号和用户编号查询喜欢
     * @param custom
     * @return
     */
    public Object queryFavoriteByProductIdAndUserId(FavoriteCustom custom);

}
