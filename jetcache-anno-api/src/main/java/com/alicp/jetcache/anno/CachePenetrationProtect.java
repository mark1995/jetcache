/**
 * Created on 2018/4/27.
 */
package com.alicp.jetcache.anno;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author huangli
 * 防止缓存穿透，即针对不存在的缓存键进行大量查询，导致系统性能下降。
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface CachePenetrationProtect {
    boolean value() default true;
    int timeout() default CacheConsts.UNDEFINED_INT;
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
