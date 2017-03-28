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

    public Object searchProduct(SeachParam param) throws Exception;

    public Object searchProducsByProdId(List list);

    public Object searchProductById(int prodId);

    public Object checkProductNum(ProductDetailQueryVo vo);

    public Object searchHistoryProduct(List<History> historys);


}
