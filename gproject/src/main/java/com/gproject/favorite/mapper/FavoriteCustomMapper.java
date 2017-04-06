package com.gproject.favorite.mapper;

import com.gproject.favorite.pojo.Favorite;
import com.gproject.favorite.pojo.FavoriteCustom;

import java.util.List;

public interface FavoriteCustomMapper {

    public List<FavoriteCustom> selectByUserId(Integer userId);

    public void insertSelective(FavoriteCustom custom);

    public void updateByPrimaryKeySelective(FavoriteCustom custom);

}