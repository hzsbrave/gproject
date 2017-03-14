package com.gproject.solr.pojo.response;

import com.gproject.category.pojo.Category;
import com.gproject.solr.pojo.vo.ProductCustom;

/**
 * MapperFactory.java
 * describe: mapper工厂,产生具体mapper
 * Author hezishan
 * Date 2017/2/28 11:03
 */
public class MapperFactory {

    public static <T> AbstractMapper<T> getMapper(Class<T> clazz){
        if(clazz== ProductCustom.class){
            return (AbstractMapper<T>) new ProductMapper();
        }
        return null;
    }

}
