package com.gproject.solr.pojo.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gproject.product.pojo.Product;
import com.gproject.solr.pojo.vo.ProductCustom;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */
public class HistoryResponse {

    private Date datetime;
    private List<ProductCustom> prods;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public List<ProductCustom> getProds() {
        return prods;
    }

    public void setProds(List<ProductCustom> prods) {
        this.prods = prods;
    }
}
