package com.gproject.solr.pojo.response;


import com.gproject.solr.pojo.vo.ProductCustom;
import org.apache.commons.beanutils.locale.converters.DecimalLocaleConverter;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.common.SolrDocument;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ProductMapper.java
 * describe: 产品solr结果处理类
 * Author hezishan
 * Date 2017/2/28 9:31
 */
public class ProductMapper extends AbstractMapper<ProductCustom> {
    @Override
    protected ProductCustom mapping(SolrDocument doc) {
        ProductCustom product = new ProductCustom();
        if (null == doc)
            return product;
        if (!StringUtils.isBlank(doc.get("productId") + ""))
            product.setProductId((Integer) doc.get("productId"));
        if (!StringUtils.isBlank(doc.get("productName") + ""))
            product.setProductName((String) doc.get("productName"));
        if (!StringUtils.isBlank(doc.get("categoryId") + ""))
            product.setCategoryId(Integer.parseInt(doc.get("categoryId") + ""));
        if (!StringUtils.isBlank(doc.get("saleNum") + ""))
            product.setSaleNum((Integer) doc.get("saleNum"));
        if (!StringUtils.isBlank(doc.get("productName") + ""))
            product.setKeyword((String) doc.get("productName"));
        if (!StringUtils.isBlank(doc.get("productPrice") + ""))
            product.setProductPrice(new BigDecimal((double) doc.get("productPrice")));
        if (!StringUtils.isBlank(doc.get("shortDesc") + ""))
            product.setShortDesc((String) doc.get("shortDesc"));
        if (!StringUtils.isBlank(doc.get("staticPage") + ""))
            product.setStaticPage((String) doc.get("staticPage"));
        if (!StringUtils.isBlank(doc.get("status") + ""))
            product.setStatus((String) doc.get("status"));
        if (!StringUtils.isBlank(doc.get("createTime") + ""))
            product.setCreateTime((Date) doc.get("createTime"));
        if (!StringUtils.isBlank(doc.get("onSaleTime") + ""))
            product.setOnSaleTime((Date) doc.get("onSaleTime"));
        if (!StringUtils.isBlank(doc.get("offSaleTime") + ""))
            product.setOffSaleTime((Date) doc.get("offSaleTime"));
        if (!StringUtils.isBlank(doc.get("thumbnail") + ""))
            product.setThumbnail((String) doc.get("thumbnail"));
        if (!StringUtils.isBlank(doc.get("prodDetailId") + ""))
            product.setProdDetailId((Integer) doc.get("prodDetailId"));
        if (!StringUtils.isBlank(doc.get("detail") + ""))
            product.setDetails((String) doc.get("detail"));
        if (!StringUtils.isBlank(doc.get("photos") + ""))
            product.setPhotos((String) doc.get("photos"));
        if (!StringUtils.isBlank(doc.get("productNum") + ""))
            product.setProductNum((Integer) doc.get("productNum"));
        return product;
    }
}
