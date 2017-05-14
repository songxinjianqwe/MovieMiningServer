package me.newsong.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by SinjinSong on 2017/3/16.
 */
public class SpringContextUtil implements ApplicationContextAware {
    private SpringContextUtil(){}
    // Spring应用上下文环境  
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     *
     * @param applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }
    public static ApplicationContext getApplicationContext() {  
        return applicationContext;  
    } 
    public static <T> T getBean(String beanId) {
        T bean = null;
        try {
            if (StringUtils.isNotEmpty(StringUtils.trim(beanId))) {
                bean = (T) applicationContext.getBean(beanId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
