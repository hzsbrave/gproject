package com.gproject.favorite.mapper;

import com.gproject.favorite.pojo.Favorite;

public interface FavoriteMapper {
    int deleteByPrimaryKey(Integer favorId);

    int insert(Favorite record);

    int insertSelective(Favorite record);

    Favorite selectByPrimaryKey(Integer favorId);

    int updateByPrimaryKeySelective(Favorite record);

    int updateByPrimaryKey(Favorite record);
}