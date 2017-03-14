package com.gproject.shoppingcartprods.service;

import com.gproject.base.mapper.BaseMapper;
import com.gproject.base.service.BaseService;
import com.gproject.shoppingcartprods.facade.ShoppingCartProdFacade;
import com.gproject.shoppingcartprods.mapper.ShoppingCartProdCustomMapper;
import com.gproject.shoppingcartprods.pojo.ShoppingCartProd;
import com.gproject.shoppingcartprods.pojo.ShoppingCartProdCustom;
import com.gproject.util.message.ResponseType;
import org.aspectj.lang.annotation.AdviceName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/3/8.
 */
@Service
public class ShoppingCartProdService extends BaseService<ShoppingCartProdCustom,Integer> implements ShoppingCartProdFacade {

    @Autowired
    private ShoppingCartProdCustomMapper shoppingCartProdCustomMapper;

    @Override
    public Object deleteShoppingProdByCartIdAndProdId(ShoppingCartProd prod) {
           if(null==prod)
               return FAIL(ResponseType.PARAMETER_NULL,"query vo is null");
           shoppingCartProdCustomMapper.deleteShoppingProdByCartIdAndProdId(prod);
           return SUCCESS();
    }

    @Override
    protected BaseMapper<ShoppingCartProdCustom, Integer> getMapper() {
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
