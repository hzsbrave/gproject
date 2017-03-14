package com.gproject.solr.base;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;

/**
 * Created by Administrator on 2017/2/28.
 */
public class SolrIndexQueryAdapter {

    private SolrIndexQuery solrIndexQuery;

    public SolrIndexQueryAdapter(SolrServer solrServer) {
        this.solrIndexQuery=new SolrIndexQuery(solrServer);
    }

    public SolrIndexQuery query(SolrQuery query){
       return solrIndexQuery.query(query);
    }
    protected SolrIndexQuery getSolrIndexQuery() {
        return solrIndexQuery;
    }

}
