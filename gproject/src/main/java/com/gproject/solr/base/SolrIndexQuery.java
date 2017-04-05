package com.gproject.solr.base;

import com.gproject.solr.pojo.response.MapperFactory;
import org.apache.solr.client.solrj.SolrResponse;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * SolrIndexQuery.java
 * describe: solr外层查询封装类
 * Author hezishan
 * Date 2017/2/28 10:45
 */
public class SolrIndexQuery {

    private SolrServer solrServer;
    private QueryResponse response;
    private Logger LOG = LoggerFactory.getLogger(getClass());

    public SolrIndexQuery(SolrServer solrServer){
        this.solrServer=solrServer;
    }
    public SolrIndexQuery query(SolrParams params){
        ModifiableSolrParams modifiableSolrParams = new ModifiableSolrParams(params);
        modifiableSolrParams.set(CommonParams.TIME_ALLOWED, 10000);
        StringBuilder log = new StringBuilder();
        Long elapsedTime = 0L;
        int qTime = 0;
        log.append(" params={ ").append(modifiableSolrParams).append(" } ");
        try {
            response = solrServer.query(modifiableSolrParams);
//            elapsedTime = response.getElapsedTime();
//            qTime = response.getQTime();
//            log.append("QTime: ").append(qTime).append(" milliscond, ElapsedTime: ").append(elapsedTime);
//            if (qTime > EnvProperties.getWarnQTime()) {
//                LOG.warn(log.toString());
//            }
//
//            if (EnvProperties.isShowAllLog() && qTime <= EnvProperties.getWarnQTime()) {
//                LOG.info(log.toString());
//            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            LOG.error(log.toString());
        }
        return this;
    }

    public QueryResponse getResponse() {
        return response;
    }

    public <T> List<T> asList(Class<T>  clazz){
        return MapperFactory.getMapper(clazz).asList(response);
    }


    public SolrDocumentList getDocumentList(){
        if(null==response||null==response.getResults())
            return null;
        return response.getResults();
    }

    public boolean isEmptyResult(QueryResponse response){
        if(null==response || null==response.getResults())
            return true;
        return response.getResults().getNumFound()<=0;
    }

    public long getTotalCount() {
        if (isEmptyResult(response)) {
            return 0;
        }

        return response.getResults().getNumFound();
    }

}
