package com.gproject.solr.controller;

import com.gproject.solr.pojo.query.ProductDetailQueryVo;
import com.gproject.solr.pojo.query.SeachParam;
import com.gproject.solr.service.ProductSolrService;
import com.gproject.user.pojo.User;
import com.gproject.util.message.RequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * SolrController.java
 * describe:
 * Author hezishan
 * Date 2017/2/28 14:48
 */
@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductSolrService productSolrService;

    @ResponseBody
    @RequestMapping(value = "searchProduct", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object searchProduct(@RequestBody RequestMessage<SeachParam> context) throws Exception{
        SeachParam param=context.getRequestContext();
        return productSolrService.searchProduct(param);
    }

    @ResponseBody
    @RequestMapping(value = "searchProdsByIds", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object searchProdsByIds(@RequestBody RequestMessage<SeachParam> context) throws Exception{
        SeachParam param=context.getRequestContext();
        return productSolrService.searchProducsByProdId(param.getProdIds());
    }

    @ResponseBody
    @RequestMapping(value = "searchProdsById", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object searchProdsById(@RequestBody RequestMessage<SeachParam> context) throws Exception{
        SeachParam param=context.getRequestContext();
        return productSolrService.searchProductById(param.getProductId());
    }

    @ResponseBody
    @RequestMapping(value = "checkProductNum", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Object checkProductNum(@RequestBody RequestMessage<ProductDetailQueryVo> context) throws Exception{
        ProductDetailQueryVo param=context.getRequestContext();
        return productSolrService.checkProductNum(param);
    }





}
