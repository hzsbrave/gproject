package com.gproject.recommend.service;

import com.gproject.base.mapper.BaseMapper;
import com.gproject.base.service.BaseService;
import com.gproject.recommend.facade.RecommendFacade;
import com.gproject.recommend.mapper.RecommendCustomMapper;
import com.gproject.recommend.pojo.RecommendCustom;
import com.gproject.util.message.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2017/3/31.
 */
@Service
public class RecommendService extends BaseService<RecommendCustom,Integer> implements RecommendFacade {

    @Autowired
    private RecommendCustomMapper recommendCustomMapper;

    @Override
    protected BaseMapper<RecommendCustom, Integer> getMapper() {
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

    @Override
    public Object insertRecommenProduct(RecommendCustom custom) {
        if(custom==null)
            return FAIL(ResponseType.PARAMETER_NULL,"推荐产品信息为空");
        try {
            RecommendCustom query = recommendCustomMapper.selectByUserIdAndCategoryId(custom);
            if (query == null) {
                custom.setCreateTime(new Date());
                custom.setLastUpdateTime(new Date());
                custom.setCreateBy(0);
                custom.setLastUpdataBy(0);
                recommendCustomMapper.insertRecommendProduct(custom);
            }else{
                query.setLastUpdateTime(new Date());
                recommendCustomMapper.updateByPrimaryKeySelective(query);
            }
        }catch (Exception e){
            new RuntimeException("添加推荐产品信息失败");
        }
        return SUCCESS("推荐产品已经存在");
    }
}
