package com.gproject.order.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gproject.base.mapper.BaseMapper;
import com.gproject.base.service.BaseService;
import com.gproject.complaint.mapper.ComplaintCustomMapper;
import com.gproject.complaint.pojo.ComplaintCustom;
import com.gproject.order.facade.OrderFacade;
import com.gproject.order.mapper.OrderCustomMapper;
import com.gproject.order.pojo.Order;
import com.gproject.order.pojo.OrderCustom;
import com.gproject.order.pojo.vo.*;
import com.gproject.orderdetail.mapper.OrderDetailCustomMapper;
import com.gproject.orderdetail.pojo.OrderDetailCustom;
import com.gproject.orderdetail.pojo.OrderDetailVo;
import com.gproject.paypal.facade.PayPalFacade;
import com.gproject.shoppingcart.mapper.ShoppingCartCustomMapper;
import com.gproject.shoppingcart.pojo.ShoppingCartCustom;
import com.gproject.shoppingcart.pojo.vo.ShoppingProdVo;
import com.gproject.shoppingcartprods.mapper.ShoppingCartProdCustomMapper;
import com.gproject.solr.Util.QueryUtils;
import com.gproject.solr.base.ProductSolrIndexQueryAdapter;
import com.gproject.solr.base.SolrIndexQuery;
import com.gproject.solr.base.SolrServerFactory;
import com.gproject.solr.pojo.vo.ProductCustom;
import com.gproject.util.message.ResponseType;
import com.gproject.util.redis.core.RedisTemplate;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
@Service
public class OrderService extends BaseService<Order, Integer> implements OrderFacade {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private OrderCustomMapper orderCustomMapper;
    @Autowired
    private OrderDetailCustomMapper orderDetailCustomMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SolrServerFactory factory;
    @Autowired
    private ShoppingCartCustomMapper shoppingCartCustomMapper;
    @Autowired
    private ShoppingCartProdCustomMapper cartProdCustomMapper;
    @Autowired
    private ComplaintCustomMapper complaintCustomMapper;


    @Override
    protected BaseMapper<Order, Integer> getMapper() {
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
    public Object insertOrder(OrderInsertVo vo) {
        if (null == vo)
            return FAIL(ResponseType.PARAMETER_NULL, "insert vo is null");
        //比较库存量和销售量
        List<OrderProduct> prods = vo.getProds();
        if (null == prods && prods.size() == 0)
            return FAIL(ResponseType.PARAMETER_NULL, "no products");

        //////////////////////////////////////////对比库存量/////////////////////////////////////
        Gson gson = new Gson();
        int orderId = 0;
        ProductCustom cacheproduct = new ProductCustom();
        for (OrderProduct product : prods) {
            //判断加入购物车的数量和库存量对比
            String json = (String) redisTemplate.get("productId" + product.getProductId());
            //缓存不存在，去solr查询
            if (StringUtils.isBlank(json)) {
                List<ProductCustom> lists = new ArrayList<ProductCustom>();
                //查询语句
                SolrQuery query = QueryUtils.buildQueryByProdId(product.getProductId());
                //产品查询
                SolrIndexQuery solrIndexQuery = new ProductSolrIndexQueryAdapter(factory.getProductServer()).query(query);
                lists = solrIndexQuery.asList(ProductCustom.class);
                if (null != prods && 0 != prods.size()) {
                    cacheproduct = lists.get(0);
                }
                //放入缓存
                redisTemplate.set("productId" + product.getProductId(), gson.toJson(lists.get(0)));
            } else {
                //缓存中存在，和缓存的库存量相比
                Type type = new TypeToken<ProductCustom>() {
                }.getType();
                cacheproduct = gson.fromJson(json.toString(), type);
            }
            if (product.getNum() > cacheproduct.getProductNum())
                return FAIL(ResponseType.PRODUCT_NUM_OVER, product.getProductId() + "");
        }

        //////////////////////////////////记录订单数//////////////////////////////////
        Object object;
        try {
            OrderCustom cus = getOrderCustom(vo);
            //记录订单,插入后返回自增主键到对象
            orderCustomMapper.insertOrder(cus);
            //记录订单详情
            List<OrderDetailCustom> details = new ArrayList<OrderDetailCustom>();

            //设置订单id
            orderId = cus.getOrderId();
            vo.setOrderId(cus.getOrderId());
            for (int i = 0; i < prods.size(); i++) {
                OrderDetailCustom custom = new OrderDetailCustom();
                custom.setOrderId(cus.getOrderId());
                custom.setProductId(prods.get(i).getProductId());
                custom.setNum(prods.get(i).getNum());
                custom.setProductPrice(prods.get(i).getProductPrice());
                BigDecimal sum = prods.get(i).getProductPrice().multiply(new BigDecimal(prods.get(i).getNum()));
                custom.setSumFee(sum);
                details.add(custom);
            }
            System.out.println(details);
            //批量插入订单详情
            orderDetailCustomMapper.insertOrderDetailBatch(details);
            //在购物车中删除已生成订单的商品
            ShoppingCartCustom shoppingCartCustom = shoppingCartCustomMapper.queryShoppingCartByUserId(vo.getUserId());
            List<ShoppingProdVo> shoppinglists = new ArrayList<ShoppingProdVo>();
            if (shoppingCartCustom != null) {
                for (OrderProduct product : prods) {
                    ShoppingProdVo shoppingVo = new ShoppingProdVo();
                    shoppingVo.setCartId(shoppingCartCustom.getCartId());
                    shoppingVo.setProductId(product.getProductId());
                    shoppinglists.add(shoppingVo);
                }
            }
            cartProdCustomMapper.deleteShoppingProdBatch(shoppinglists);
        } catch (Exception e) {
            logger.info("insert order error.");
            throw new RuntimeException("insert order error.");

        }
        return SUCCESS(orderId);
    }

    public Object queryOrderForUser(OrderQueryVo vo) throws Exception{
        if (null == vo)
            return FAIL(ResponseType.PARAMETER_NULL, "query vo is null");
        List<OrderDetailAll> all = orderCustomMapper.queryOrderForUser(vo);
        for (OrderDetailAll order : all) {
            String timestamp=order.getCreateTime()+"";
            order.setCreateTime(stampToDate(timestamp));
        }
        System.out.println(all.toString());
        return SUCCESS(all);
    }

    public Object queryOrderForUserRefund(OrderQueryVo vo) throws Exception {
        if (null == vo)
            return FAIL(ResponseType.PARAMETER_NULL, "query vo is null");
        List<OrderDetailAll> all = orderCustomMapper.queryOrderForUser(vo);
        for (OrderDetailAll order : all) {
            for(OrderDetailVo detail:order.getOrderDetailVos()){
                ComplaintCustom custom=new ComplaintCustom();
                custom.setOrderId(order.getOrderId());
                custom.setOrderDetailId(detail.getOrderDetailId());
                int count=complaintCustomMapper.queryComplaintCondition(custom);
                detail.setComplaint(count);
                detail.getProduct().setStaticPage(count+"");
            }
            String timestamp=order.getCreateTime()+"";
            order.setCreateTime(stampToDate(timestamp));
        }
        System.out.println(all.toString());
        return SUCCESS(all);
    }

    /*
 * 将时间戳转换为时间
 */
    public static Date stampToDate(String s)  throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = simpleDateFormat.format(new Date(s));
        Date date=simpleDateFormat.parse(d);
        System.out.println(date);
        return date;
    }

    private OrderCustom getOrderCustom(OrderInsertVo vo) throws Exception {

        OrderCustom cus = new OrderCustom();
        cus.setUserId(vo.getUserId());
        cus.setAddressId(vo.getAddressId());
        cus.setExpressFee(vo.getExpressFee());
        cus.setProdNum(vo.getProdNum());
        //订单号，当前时间撮
        String orderNum = System.currentTimeMillis() + "";
        cus.setOrderNumber(orderNum);
        //订单状态：1:未付款、2:待发货、3:待签收、7:完成、9:申诉、11:退款成功
        cus.setState(OrderState.NO_PAY);
        cus.setTotalFee(vo.getTotalFee());
        //获取系统当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dataStr = sdf.format(new Date());
        cus.setCreateTime(sdf.parse(dataStr));
        return cus;
    }


}
