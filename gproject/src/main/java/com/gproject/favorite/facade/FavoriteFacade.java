package com.gproject.favorite.facade;

import com.gproject.favorite.pojo.FavoriteCustom;

/**
 * Created by Administrator on 2017/4/6.
 */
public interface FavoriteFacade {

    public Object insertFavorite(FavoriteCustom custom);

    public Object queryFavoriteByUserId(Integer userId);

}
