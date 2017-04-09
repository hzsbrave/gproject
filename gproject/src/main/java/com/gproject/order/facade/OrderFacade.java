package com.gproject.order.facade;

import com.gproject.order.pojo.OrderCustom;
import com.gproject.order.pojo.vo.OrderInsertVo;
import com.gproject.order.pojo.vo.OrderQueryVo;

/**
 * Created by Administrator on 2017/3/10.
 */
public interface OrderFacade {

    /**
     * 添加订单信息
     * 1.请求solr，搜索商品信息，显示在页面
     * 2.插入订单信息到order表，并返回自增主键
     * 3.一个订单对应多个订单详情信息，插入到order_detail表中
     * 4.已经生成订单的商品信息从购物车表中上删除，删除shopping_cart表中对应用户的商品信息
     * @param vo
     * @return
     * @throws Exception
     */
    public Object insertOrder(OrderInsertVo vo) throws Exception;

    /**
     * 为用户查询所有订单信息
     * @param vo
     * @return
     * @throws Exception
     */
    public Object queryOrderForUser(OrderQueryVo vo) throws Exception;

    /**
     * 为用户查询所有申诉的订单信息
     * @param vo
     * @return
     * @throws Exception
     */
    public Object queryOrderForUserCustomerService(OrderQueryVo vo) throws Exception;

    /**
     * 根据订单编号查询订单及订单详情信息
     * @param vo
     * @return
     * @throws Exception
     */
    public Object queryOrderDetailByOrderId(OrderQueryVo vo) throws Exception;

    /**
     * 根据订单编号更新订单情况
     * @param vo
     * @return
     */
    public Object updateOrderDetailByOrderId(OrderCustom vo);

    /**
     * 货到付款方式
     * 1.将订单付款信息保存在流水账running_account表中，并更新订单状态和订单付款方式
     * 2.跳转货到付款成功页面
     * @param orderCustom
     * @return
     */
    public Object updateOrderInfo(OrderCustom orderCustom);

}
