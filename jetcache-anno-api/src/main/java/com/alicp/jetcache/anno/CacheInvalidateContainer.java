package com.alicp.jetcache.anno;

import java.lang.annotation.*;

/**
 * @author <a href="scolia@qq.com">scolia</a>
 */

/**
 * 支持指定多个失效的key
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CacheInvalidateContainer {

    CacheInvalidate[] value();
}
