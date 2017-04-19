package com.gproject.shoppingcartprods.facade;

import com.gproject.shoppingcartprods.pojo.ShoppingCartProd;

/**
 * Created by Administrator on 2017/3/8.
 */
public interface ShoppingCartProdFacade {

    /**
     * 根据购物车详情编号，删除记录
     * @param prod
     * @return
     */
    Object deleteShoppingProdByCartIdAndProdId(ShoppingCartProd prod);

}
