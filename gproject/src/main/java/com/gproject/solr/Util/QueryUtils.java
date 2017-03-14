package com.gproject.solr.Util;

import com.gproject.solr.pojo.query.SeachParam;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;

import java.util.List;

/**
 * QueryUtils.java
 * describe: 生成solr查询语句工具类
 * Author hezishan
 * Date 2017/2/27 22:23
 */
public class QueryUtils {

    /**
     * 产品搜索的查询语句
     *
     * @param param
     * @return
     */
    public static SolrQuery buildProductSearchQuery(SeachParam param) {
        String q = "*:*";
        if (null == param)
            return null;
        String keyword = param.getKeyword();
        if (!StringUtils.isBlank(keyword))
            q = "keyword:\"" + keyword + "\"^7000 OR (productName:(" + keyword + ") OR searchText:("
                    + keyword + "))^500 ";
        SolrQuery query = new SolrQuery().setQuery(q).setStart(0).setRows(800);
        //如果叶子节点不为0，添加叶子节点过滤
        if (0 != param.getCategoryId())
            query.addFilterQuery("categoryId:" + param.getCategoryId());
        //排序方式
        int priceFlag = param.getPriceFlag();
        int saleFlag = param.getSaleFlag();
        System.out.println(priceFlag + "----" + saleFlag);
        query = dealSort(param.getPriceFlag(), param.getSaleFlag(), query);
        return query;
    }

    /**
     * 处理价格和销售量排序
     *
     * @param priceFlag 价格标识
     * @param saleFlag  销售量标识
     * @param query     solrquery
     * @return
     */
    public static SolrQuery dealSort(int priceFlag, int saleFlag, SolrQuery query) {
        System.out.println(priceFlag + "----" + saleFlag);
        //没有排序
        if (0 == priceFlag && 0 == saleFlag) {
            return query.addSort("createTime", SolrQuery.ORDER.desc);
        }
        //单销售量排序
        if (0 == priceFlag && 1 == saleFlag) {
            return query.addSort("saleNum", SolrQuery.ORDER.asc);
        }
        if (0 == priceFlag && 2 == saleFlag) {
            return query.addSort("saleNum", SolrQuery.ORDER.desc);
        }
        //单价格排序
        if (1 == priceFlag && 0 == saleFlag) {
            return query.addSort("productPrice", SolrQuery.ORDER.asc);
        }
        if (2 == priceFlag && 0 == saleFlag) {
            return query.addSort("productPrice", SolrQuery.ORDER.desc);
        }
        //单价升序 销售量升序 排序
        if (1 == priceFlag && 1 == saleFlag) {
            return query.addSort("productPrice", SolrQuery.ORDER.asc).addSort("saleNum", SolrQuery.ORDER.asc);
        }
        //单价升序 销售量降序 排序
        if (1 == priceFlag && 2 == saleFlag) {
            return query.addSort("productPrice", SolrQuery.ORDER.asc).addSort("saleNum", SolrQuery.ORDER.desc);
        }
        //单价降序 销售量升序 排序
        if (2 == priceFlag && 1 == saleFlag) {
            return query.addSort("productPrice", SolrQuery.ORDER.desc).addSort("saleNum", SolrQuery.ORDER.asc);
        }
        //单价降序 销售量降序 排序
        return query.addSort("productPrice", SolrQuery.ORDER.desc).addSort("saleNum", SolrQuery.ORDER.desc);
    }

    /**
     * 根据产品id列表建立查询条件
     * @param prodIds
     * @return
     */
    public static SolrQuery buildQueryByProdIds(List prodIds) {
        SolrQuery query = new SolrQuery();
        String  q="productId: ( ";
        if (null != prodIds || 0 != prodIds.size()) {
            for (int i = 0; i < prodIds.size(); i++) {
                q=q+" "+prodIds.get(i).toString();
            }
        }
        q=q+" )";
        System.out.println(q);
        query=query.setQuery(q);
        return query;
    }

    public static SolrQuery buildQueryByProdId(int prodId) {
        SolrQuery query = new SolrQuery();
        String  q="productId:  "+prodId;
        query=query.setQuery(q);
        return query;
    }

}
