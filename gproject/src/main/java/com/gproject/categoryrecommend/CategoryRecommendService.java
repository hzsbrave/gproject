package com.gproject.categoryrecommend;

import com.google.gson.annotations.SerializedName;
import com.gproject.base.mapper.BaseMapper;
import com.gproject.base.service.BaseService;
import com.gproject.category.mapper.CategoryMapperCustom;
import com.gproject.category.pojo.CategoryCustom;
import com.gproject.category.pojo.vo.CategoryExample;
import com.gproject.categoryrecommend.facade.CategoryRecommendFacade;
import com.gproject.categoryrecommend.mapper.CategoryRecommendCustomMapper;
import com.gproject.categoryrecommend.pojo.CategoryRecommend;
import com.gproject.categoryrecommend.pojo.CategoryRecommendCustom;
import com.gproject.categoryrecommend.pojo.vo.HotCategoryResp;
import com.gproject.solr.facade.ProductSolrFacade;
import com.gproject.solr.pojo.query.SeachParam;
import com.gproject.util.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/2.
 */
@Service
public class CategoryRecommendService extends BaseService<CategoryRecommend, Integer> implements CategoryRecommendFacade {

    @Autowired
    private CategoryRecommendCustomMapper categoryRecommendMapper;
    @Autowired
    private CategoryMapperCustom categoryMapper;
    @Autowired
    private ProductSolrFacade productSolr;

    @Override
    protected BaseMapper<CategoryRecommend, Integer> getMapper() {
        return null;
    }

    @Override
    protected String getMapperNameSpace() {
        return null;
    }

    @Override
    protected String getDefalultDatabase() {
        return null;
    }

    @Override
    public Object queryHotCategoryProduct(Integer num) throws  Exception {
        List<HotCategoryResp> respList = new ArrayList<HotCategoryResp>();
        if (num == null)
            num = 2;
        List<CategoryRecommendCustom> cates = categoryRecommendMapper.selectCategoryRecommendByNum(num);
        for (CategoryRecommendCustom cate : cates) {
            HotCategoryResp resp = new HotCategoryResp();
            resp.setCategoryId(cate.getCategoryId());
            CategoryExample condition=new CategoryExample();
            condition.setCondition(cate.getCategoryId());
            CategoryCustom custom=categoryMapper.selectCategoryById(condition);
            resp.setCategoryName(custom.getCategoryName());
            List param=new ArrayList();
            param.add(cate.getCategoryId());
            ResponseMessage msg=(ResponseMessage) productSolr.searchRecommendProdsByCategoryId(param);
            if(msg!=null)
                resp.setProducts(msg.getResult());
            respList.add(resp);
        }
        return SUCCESS(respList);
    }
}
