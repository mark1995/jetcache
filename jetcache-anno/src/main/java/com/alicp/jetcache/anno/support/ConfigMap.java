/**
 * Created on 2018/1/22.
 */
package com.alicp.jetcache.anno.support;

import com.alicp.jetcache.anno.CacheConsts;
import com.alicp.jetcache.anno.method.CacheInvokeConfig;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author huangli
 */
public class ConfigMap {
    /**
     * 缓存对应的缓存配置
     */
    private ConcurrentHashMap<String, CacheInvokeConfig> methodInfoMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, CachedAnnoConfig> cacheNameMap = new ConcurrentHashMap<>();

    public void putByMethodInfo(String key, CacheInvokeConfig config) {
        methodInfoMap.put(key, config);
        CachedAnnoConfig cac = config.getCachedAnnoConfig();
        if (cac != null && !CacheConsts.isUndefined(cac.getName())) {
            cacheNameMap.put(cac.getArea() + "_" + cac.getName(), cac);
        }
    }

    public CacheInvokeConfig getByMethodInfo(String key) {
        return methodInfoMap.get(key);
    }

    public CachedAnnoConfig getByCacheName(String area, String cacheName) {
        return cacheNameMap.get(area + "_" + cacheName);
    }
}
