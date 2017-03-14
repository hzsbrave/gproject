package com.gproject.shoppingcart.facade;

import com.gproject.shoppingcart.pojo.ShoppingCartCustom;
import com.gproject.shoppingcart.pojo.vo.ShoppingCartQueryVo;

/**
 * ShoppingCartFacade.java
 * describe:
 * Author hezishan
 * Date 2017/3/4 19:21
 */
public interface ShoppingCartFacade {

    public Object queryShoppingCartDetail(ShoppingCartQueryVo vo);

    public Object insertShoppingCart(ShoppingCartQueryVo vo);

}
