package com.gproject.util.placeholder;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * modify by hezishan
 * 
 * @Description:�Զ�����������ļ���
 * ��Ҫ��дprocessProperties����
 * @time:2016��11��24�� ����5:49:07
 * 
 */
public class CustomPropertyPlaceHolder extends PropertyPlaceholderConfigurer {

	/**
     * ����������Ϣ
     */
    private static Map<String, String> contextMap = new HashMap<String, String>();

    /**
     * ��ȡ���ò���
     *
     * @param key key��Ϣ
     * @return String
     */
    public static String getStrPro(String key) {
        return contextMap.get(key);
    }

    /**
     * ��ȡ�������ò���ֵ
     *
     * @param key key��Ϣ
     * @return int
     */
    public static int getIntPro(String key) {
        try {
            String val = contextMap.get(key);
            return Integer.parseInt(val);
        } catch (Exception e) {
        	e.printStackTrace();
           
        }
        return 0;
    }

    /**
     * ����spring�����е� properties �ļ�
     *
     * @param beanFactoryToProcess ����
     * @param props �����ļ�
     * @throws BeansException  
     */
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
                                     Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
       

        StringBuilder sb = new StringBuilder();
        sb.append("\r\n");
        String key = null;
        String value = null;
        for (Object k : props.keySet()) {
            key = (String) k;
            value = (String) props.get(key);
            sb.append("[ ").append(key).append("\t:\t").append(value).append(" ]\r\n");
            
            contextMap.put(key, value);
        }
       
    }
	
}
