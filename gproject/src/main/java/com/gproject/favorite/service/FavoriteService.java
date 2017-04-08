package com.gproject.favorite.service;

import com.gproject.base.mapper.BaseMapper;
import com.gproject.base.service.BaseService;
import com.gproject.favorite.facade.FavoriteFacade;
import com.gproject.favorite.mapper.FavoriteCustomMapper;
import com.gproject.favorite.pojo.Favorite;
import com.gproject.favorite.pojo.FavoriteCustom;
import com.gproject.solr.facade.ProductSolrFacade;
import com.gproject.util.message.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 */
@Service
public class FavoriteService extends BaseService<FavoriteCustom,Integer> implements FavoriteFacade {

    @Autowired
    private FavoriteCustomMapper favoriteCustomMapper;
    @Autowired
    private ProductSolrFacade productSolrFacade;


    @Override
    public Object insertFavorite(FavoriteCustom custom) {
        if(custom==null)
            return FAIL(ResponseType.PARAMETER_NULL,"收藏参数为空");
        int result=0;
        try{
            FavoriteCustom query=favoriteCustomMapper.selectByUserIdAndProductId(custom);
            if(query==null){
                custom.setCreateTime(new Date());
                custom.setLastUpdateTime(new Date());
                favoriteCustomMapper.insertSelective(custom);
                result=1;
            }else{
                favoriteCustomMapper.deleteByPrimaryKey(query.getFavorId());
            }
        }catch (Exception e){
            new RuntimeException();
        }
        return SUCCESS(result);
    }

    @Override
    public Object queryFavoriteByUserId(Integer userId) {
        if(userId==null)
            return FAIL(ResponseType.PARAMETER_NULL,"用戶參數为空");
        List customs=favoriteCustomMapper.selectByUserId(userId);
        Object object=productSolrFacade.searchProducsByProdId(customs);
        return object;
    }

    @Override
    public Object queryFavoriteByProductIdAndUserId(FavoriteCustom custom) {
        if(custom==null)
            return FAIL(ResponseType.PARAMETER_NULL,"用戶參數为空");
        FavoriteCustom query=favoriteCustomMapper.selectByUserIdAndProductId(custom);
        if(query==null)
            return SUCCESS(false);
        return SUCCESS(true);
    }

    @Override
    protected BaseMapper<FavoriteCustom, Integer> getMapper() {
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
