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

    /**
     * 获取用户购物车信息
     * @param vo
     * @return
     */
    public Object queryShoppingCartDetail(ShoppingCartQueryVo vo);

    /**
     * 加入购物车（一个用户对应一个购物车）
     * 1.根据用户编号查询用户是否存在购物车
     * 2.若存在，则获取购物车编号，若购物车中的产品库存量充足，则将购物车详情添加到shoppingcartprods表中，若库存量不充足则提示库存量不足
     * 3.若不存在，则为用户创建购物车，即在shoppingcart表插入数据并返回自增主键，若购物车中的产品库存量充足，则将购物车详情添加到shoppingcartprods表中，若库存量不充足则提示库存量不足
     * @param vo
     * @return
     */
    public Object insertShoppingCart(ShoppingCartQueryVo vo);

}
