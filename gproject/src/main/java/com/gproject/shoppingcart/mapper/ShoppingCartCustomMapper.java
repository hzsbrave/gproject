package com.gproject.shoppingcart.mapper;

import com.gproject.shoppingcart.pojo.ShoppingCart;
import com.gproject.shoppingcart.pojo.ShoppingCartCustom;
import com.gproject.shoppingcart.pojo.vo.ShoppingCartQueryVo;

public interface ShoppingCartCustomMapper {
    /**
     * 查询购物车的详情
     * @param vo
     * @return
     */
    public ShoppingCartCustom queryShoppingCart(ShoppingCartQueryVo vo);

    /**
     * 插入数据并返回自增主键
     * @param cumstom
     * @return
     */
    public int insertShoppingCart(ShoppingCart cumstom);

    /**
     * 根据用户id查询购物车
     * @param userId
     * @return
     */
    public ShoppingCartCustom queryShoppingCartByUserId(Integer userId);
}