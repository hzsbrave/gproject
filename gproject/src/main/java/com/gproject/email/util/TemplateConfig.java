package com.gproject.email.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.util.Map;

/**
 * modify by hezishan
 *
 * Description:TODO 模板加载
 * time:2017年2月13日 下午4:58:28
 */
public class TemplateConfig {

    public static String getTemplate(String tem_type, Map<String, String> root) throws Exception {

        //1.创建配置实例Cofiguration  
        Configuration cfg = new Configuration();
       // cfg.setClassForTemplateLoading(TemplateConfig.class, "/templates");
        cfg.setDirectoryForTemplateLoading(new File("D:\\gproject\\gproject\\src\\main\\java\\com\\gproject\\email\\util\\templates"));
        //获取模板（template）  
        Template template = cfg.getTemplate(tem_type+".ftl");
        String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, root);
        System.out.println(htmlText);
        return htmlText;

    }
}  