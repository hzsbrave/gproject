package com.gproject.solr.pojo.response;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ExecutionError;
import org.apache.lucene.document.Document;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * AbstractMapper.java
 * describe: 抽象mapper类接口
 * Author hezishan
 * Date 2017/2/27 23:15
 */
public abstract class AbstractMapper<T> {

    /**
     * 子类中需要重载这个方法，具体处理SolrDocument中的内容
     * @param doc
     * @return
     */
    protected abstract T mapping(SolrDocument doc);

    /**
     * 判断查询结果是否为空
     * @param response
     * @return
     */
    private boolean isEmptyResponse(QueryResponse response){
        if(null==response || null==response.getResults()){
            return true;
        }
        return response.getResults().getNumFound()<=0;
    }

    /**
     * 将查询结果转换为一个list
     * @param response
     * @return
     */
    public List<T> asList(QueryResponse response){
        if(isEmptyResponse(response))
            return Collections.emptyList();
        SolrDocumentList doclist=response.getResults();
        List<T> list= Lists.newArrayList();
        try{
            for(SolrDocument doc: doclist){
                list.add(mapping(doc));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
       return list;
    }


}
