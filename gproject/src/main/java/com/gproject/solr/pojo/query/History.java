package com.gproject.solr.pojo.query;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */
public class History {

    private Date datetime;
    private List<Integer> prods;

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public List<Integer> getProds() {
        return prods;
    }

    public void setProds(List<Integer> prods) {
        this.prods = prods;
    }
}
