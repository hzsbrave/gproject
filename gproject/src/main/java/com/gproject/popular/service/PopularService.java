package com.gproject.popular.service;

import com.gproject.base.mapper.BaseMapper;
import com.gproject.base.service.BaseService;
import com.gproject.popular.facade.PopularFacade;
import com.gproject.popular.mapper.PopularCustomMapper;
import com.gproject.popular.mapper.PopularMapper;
import com.gproject.popular.pojo.Popular;
import com.gproject.popular.pojo.PopularCustom;
import com.gproject.popular.pojo.vo.PopularVo;
import com.gproject.util.message.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/3/3.
 */
@Service
public class PopularService extends  BaseService<Popular,Integer> implements PopularFacade  {

    @Autowired
    private PopularCustomMapper popularCustomMapper;

    @Override
    public Object queryPopularLimit(PopularVo vo) {
        if(null == vo)
            return FAIL(ResponseType.PARAMETER_NULL,"parameter is null");
        List<PopularCustom> popularList=popularCustomMapper.queryPopularLimit(vo);
        System.out.println(popularList);
        return SUCCESS(popularList);
    }

    @Override
    protected BaseMapper<Popular, Integer> getMapper() {
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
