/**
 * Created on  13-09-04
 */
package com.alicp.jetcache.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author huangli
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cached {
    /**
     * If you want to use multi backend cache system, you can setup multi "cache area" in configuration,
     * this attribute specifies the name of the "cache area" you want to use.
     * @return the name of cache area
     */
    String area() default CacheConsts.DEFAULT_AREA;

    /**
     * The name of this Cache instance, optional.
     * If you do not specify, JetCache will auto generate one.
     * The name is used to display statistics information and as part of key prefix when using a remote cache.
     * Do not assign same name to different @Cached annotation which with same area.
     * @return the name of the cache
     *
     * 设计的比较好，缓存实例命名，用来统计
     */
    String name() default CacheConsts.UNDEFINED_STRING;


    /**
     * Specify whether the method caching is enabled.
     * If set to false, you can enable it in thread context using
     * {@code CacheContext.enableCache(Supplier<T> callback)}
     * @return if the method cache is enabled
     */
    boolean enabled() default CacheConsts.DEFAULT_ENABLED;

    /**
     * Specify the time unit of expire.
     * @return the time unit of expire time
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * The expire time. Use global config if the attribute value is absent,
     * and if the global config is not defined either, use infinity instead.
     * @return the expire time
     */
    int expire() default CacheConsts.UNDEFINED_INT;

    /**
     * Use to specify the local cache expire time when cacheType=CacheType.BOTH, use "expire" if absent.
     * @return the local cache expire time
     */
    int localExpire() default CacheConsts.UNDEFINED_INT;

    /**
     * Type of the Cache instance. May be CacheType.REMOTE, CacheType.LOCAL, CacheType.BOTH.
     * Create a two level cache (local+remote) when value is CacheType.BOTH.
     * @return cache type of the method cache
     * 缓存类型，多级缓存
     */
    CacheType cacheType() default CacheType.REMOTE;

    /**
     * if cacheType is CacheType.BOTH and the remote cache supports broadcast (or BroadcastManager bean exists),
     * invalidate local cache of all process after put/remove operation.
     * @return should sync local cache
     *
     * 更新操作会同步到其他进程的广播
     */
    boolean syncLocal() default false;

    /**
     * Specify max elements in local memory when cacheType is CacheType.LOCAL or CacheType.BOTH.
     * Use global config if the attribute value is absent, and if the global config is not defined either,
     * use CacheConsts.DEFAULT_LOCAL_LIMIT instead.
     * @return local maximal elements of the LOCAL/BOTH cache
     *
     * 本地缓存的最大元素数量
     */
    int localLimit() default CacheConsts.UNDEFINED_INT;

    /**
     * Specify the serialization policy of remote cache when cacheType is CacheType.REMOTE or CacheType.BOTH.
     * The JetCache build-in serialPolicy are SerialPolicy.JAVA or SerialPolicy.KRYO.
     * Use global config if the attribute value is absent, and if the global config is not defined either,
     * use SerialPolicy.JAVA instead.
     * @return the serialization policy name of cache value
     *
     * 默认的序列化策略
     */
    String serialPolicy() default CacheConsts.UNDEFINED_STRING;

    /**
     * Specify the key convertor. Used to convert the complex key object.
     * The JetCache build-in keyConvertor are KeyConvertor.FASTJSON or KeyConvertor.NONE.
     * NONE indicate do not convert, FASTJSON will use fastjson to convert key object to a string.
     * Use global config if the attribute value is absent.
     * @return convertor name of cache key
     *
     * key转换器，复杂的key对象转换成字符串
     */
    String keyConvertor() default CacheConsts.UNDEFINED_STRING;

    /**
     * Specify the key by expression script, optional. If not specified,
     * use all parameters of the target method and keyConvertor to generate one.
     * @return an expression script which specifies key
     *
     * 指定缓存的key,支持spel表达式
     */
    String key() default CacheConsts.UNDEFINED_STRING;

    /**
     * Specify whether a null value should be cached.
     * @return whether a null value should be cached
     * 是否缓存空值
     */
    boolean cacheNullValue() default CacheConsts.DEFAULT_CACHE_NULL_VALUE;

    /**
     * Expression script used for conditioning the method caching, the cache is not
     * used when evaluation result is false.
     * Evaluation occurs before real method invocation.
     *
     * 什么条件下启动缓存
     */
    String condition() default CacheConsts.UNDEFINED_STRING;

    /**
     * Expression script used for conditioning the method cache updating,
     * the cache updating action is vetoed when the evaluation result is false.
     * Evaluation occurs after real method invocation so we can refer <i>result</i> in script.
     *
     * 可以支持热点缓存
     */
    String postCondition() default CacheConsts.UNDEFINED_STRING;

}
