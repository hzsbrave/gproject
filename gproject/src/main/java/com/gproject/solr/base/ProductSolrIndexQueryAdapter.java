package com.gproject.solr.base;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ProductSolrIndexQueryAdapter.java
 * describe:产品搜索适配类
 * Author hezishan
 * Date 2017/2/28 14:13
 */
public class ProductSolrIndexQueryAdapter extends SolrIndexQueryAdapter {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    public ProductSolrIndexQueryAdapter(SolrServer solrServer) {
        super(solrServer);
    }

    public SolrIndexQuery query(SolrQuery query) {
        LOG.info("SolrQuery to String:"+query.toString());
        return super.getSolrIndexQuery().query(query);
    }


}
