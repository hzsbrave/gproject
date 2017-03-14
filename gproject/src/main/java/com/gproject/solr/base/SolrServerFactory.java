package com.gproject.solr.base;

import org.apache.solr.client.solrj.SolrServer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * SolrServerFactory.java
 * describe: solr工厂类
 * Author hezishan
 * Date 2017/2/26 10:38
 */
public class SolrServerFactory implements InitializingBean {

    private SolrServer productServer;
    private SolrServer categoryServer;

    public SolrServer getProductServer() {
        return productServer;
    }

    public void setProductServer(SolrServer productServer) {
        this.productServer = productServer;
    }

    public SolrServer getCategoryServer() {
        return categoryServer;
    }

    public void setCategoryServer(SolrServer categoryServer) {
        this.categoryServer = categoryServer;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(productServer,"productServer cannot be null");
        Assert.notNull(categoryServer,"categoryServer cannot be null");
    }
}
