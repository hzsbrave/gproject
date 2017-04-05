package com.gproject.solr.pojo.query;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */
public class History {

    private Date datetime;
    private List<String> prods;

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public List<String> getProds() {
        return prods;
    }

    public void setProds(List<String> prods) {
        this.prods = prods;
    }
}
