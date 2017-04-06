package com.gproject.solr.facade;

import com.gproject.solr.pojo.query.History;
import com.gproject.solr.pojo.query.HistoryVO;
import com.gproject.solr.pojo.query.ProductDetailQueryVo;
import com.gproject.solr.pojo.query.SeachParam;

import java.util.List;

/**
 * Created by Administrator on 2017/3/6.
 */
public interface ProductSolrFacade {

    /**
     * 根据关键词和筛选条件查询商品
     * 将关键词分别和商品编号、商品名字、商品复合关键词按照一定的权重匹配
     * 若筛选条件存在则拼接筛选条件
     * 若缓存中存在由 关键词和筛选条件 为key的结果集，则从缓存中取出
     * 若缓存中不存在，则请求solr搜索并将 关键词和筛选条件 为key 保存在缓存中
     * @param param
     * @return
     * @throws Exception
     */
    public Object searchProduct(SeachParam param) throws Exception;

    /**
     * 根据产品编号列表搜索产品
     * 若缓存中存在该商品，则从缓存中取出
     * 若缓存中不存在，则请求搜索接口并存放在缓存中
     * @param list
     * @return
     */
    public Object searchProducsByProdId(List list);

    /**
     * 根据产品id搜索产品（商品详情页请求）
     * 若搜索由登录用户发起，则将用户的搜索行为的分类编号记录在个性化推荐表
     * 若搜索是由游客发去，则用户搜索行为保存到前台缓存，后台不处理
     * @param prodId 产品编号
     * @param userId 用户编号
     * @return
     */
    public Object searchProductById(int prodId,Integer userId);

    /**
     *校验产品库存量
     * @param vo 产品信息（产品编号、数量）
     * @return
     */
    public Object checkProductNum(ProductDetailQueryVo vo);

    /**
     * 搜索浏览历史产品
     * 浏览历史产品信息根据日期分类
     * 一天对应多个浏览产品信息
     * @param historys 浏览历史类(日期+产品id列表)
     * @return
     */
    public Object searchHistoryProduct(List<History> historys);

    /**
     * 个性化推荐产品
     * 若是登录用户，则从用户个性化推荐表中读出最近5条分类编号，进行推荐
     * 若是游客，则根据浏览历史的分类编号推荐
     * 若分类编号不满足5个，则推荐个性化推荐分类编号+系统热门分类编号
     * @param param 查询参数
     * @return
     */
    public Object searchRecommendProds(SeachParam param);


    /**
     * 根据分类编号搜索产品
     * @param list
     * @return
     */
    public Object searchRecommendProdsByCategoryId(List list);

    /**
     * 根据分类编号搜索产品
     * 作用范围：详情页面推荐产品
     * 从搜索的产品中随机8个产品
     * @return
     */
    public Object searchRecommendProdsByCategoryId(Integer categoryId);

}
