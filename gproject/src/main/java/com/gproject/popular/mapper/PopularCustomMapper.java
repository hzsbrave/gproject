package com.gproject.popular.mapper;

import com.gproject.popular.pojo.Popular;
import com.gproject.popular.pojo.PopularCustom;
import com.gproject.popular.pojo.vo.PopularVo;

import java.util.List;

public interface PopularCustomMapper {

    public List<PopularCustom> queryPopularLimit(PopularVo vo);

}