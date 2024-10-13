/**
 * Created on  13-09-19 20:40
 */
package com.alicp.jetcache.anno.aop;

import com.alicp.jetcache.anno.support.ConfigMap;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author huangli
 * 用途：配置spring aop切面，使得切点在指定的包下生效
 */
public class CacheAdvisor extends AbstractBeanFactoryPointcutAdvisor {

    public static final String CACHE_ADVISOR_BEAN_NAME = "jetcache2.internalCacheAdvisor";

    // 注入一个缓存配置 bean
    @Autowired
    private ConfigMap cacheConfigMap;

    // 扫描的包范围
    private String[] basePackages;

    @Override
    public Pointcut getPointcut() {
        CachePointcut pointcut = new CachePointcut(basePackages);
        pointcut.setCacheConfigMap(cacheConfigMap);
        return pointcut;
    }

    public void setCacheConfigMap(ConfigMap cacheConfigMap) {
        this.cacheConfigMap = cacheConfigMap;
    }

    public void setBasePackages(String[] basePackages) {
        this.basePackages = basePackages;
    }
}
