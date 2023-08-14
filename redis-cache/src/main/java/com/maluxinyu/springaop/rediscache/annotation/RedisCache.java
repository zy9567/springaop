package com.maluxinyu.springaop.rediscache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

import com.maluxinyu.springaop.rediscache.constant.Constant;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {

    /**
     * 缓存key的名称前缀
     *
     * @return
     */
    String prefix() default Constant.CACHE_AUTO_PREFIX;

    /**
     * 缓存key的主键ID
     *
     * @return
     */
    String bizNo();

    /**
     * 参与生成健值的元素，与方法上的参数签名保持一致
     * 不设置是参数全部拼接
     *
     * @return
     */
    String[] element() default {};

    /**
     * 解析类信息
     *
     * @return
     */
    Class<? extends Object> parseClass();

    /**
     * key 过期日期 秒
     *
     * @return
     */
    int expireTime() default 60;

    /**
     * 时间单位，默认为秒
     *
     * @return
     */
    TimeUnit dateUnit() default TimeUnit.SECONDS;
}
