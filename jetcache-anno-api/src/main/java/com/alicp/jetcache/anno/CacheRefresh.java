/**
 * Created on 2018/2/1.
 */
package com.alicp.jetcache.anno;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author huangli
 */

/**
 * 当方法被调用时，其结果会被缓存。
 * 缓存会在每 60 秒自动刷新一次，确保缓存数据是最新的。
 * 如果有多个线程同时尝试刷新同一份缓存，refreshLockTimeout 可以设置锁的超时时间，避免竞争问题。
 * stopRefreshAfterLastAccess 最近多久没访问后停止刷新，
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface CacheRefresh {

    int refresh();

    int stopRefreshAfterLastAccess() default CacheConsts.UNDEFINED_INT;

    int refreshLockTimeout() default CacheConsts.UNDEFINED_INT;

    TimeUnit timeUnit() default TimeUnit.SECONDS;

}
