package com.maluxinyu.springaop.rediscache.aop;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maluxinyu.springaop.rediscache.annotation.RedisCache;
import com.maluxinyu.springaop.rediscache.service.RedisService;

import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * RedisCache注解缓存方法查询数据
 */
@Slf4j
@Component
@Aspect
public class RedisCacheAop {

    @Autowired
    private RedisService redisService;

    /**
     * 环绕通知，拦截 redis 的写入和读取，如果方法执行结果为 null 不进行保存
     *
     * @param point      切点对象
     * @param redisCache 注解内容
     */
    @Around(value = "@annotation(redisCache)", argNames = "point,redisCache")
    public Object cacheAop(ProceedingJoinPoint point, RedisCache redisCache) throws Throwable {
        Object value = null;
        Class<?> targetClass = redisCache.parseClass();

        try{
            // 从缓存中获取
            String prefix = redisCache.prefix();
            String bizNo = redisCache.bizNo();
            String key = buildKey(prefix,bizNo);
            // 如果key不为空则获取缓存中的值
            if (StringUtil.isNullOrEmpty(key)){

            }
            value = redisService.getString(key);
            if (value != null) {
                log.info("RedisCacheAop get from cache,key:{}", key);
                return value;
            }
        }catch (Throwable throwable){
            log.error("RedisCacheAop error",throwable);
        }
    }

    private String buildKey(String prefix, String bizNo) {
        if(StringUtils.isBlank(prefix) || StringUtils.isBlank(bizNo)){
            return "";
        }
        return prefix + ":" + bizNo;
    }
}
