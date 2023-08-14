package com.maluxinyu.springaop.rediscache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maluxinyu.springaop.rediscache.annotation.RedisCache;
import com.maluxinyu.springaop.rediscache.persistence.mybatisplus.mapper.SchoolMapper;
import com.maluxinyu.springaop.rediscache.persistence.mybatisplus.po.School;

/**
 * @ClassName SchoolService
 * @Author zhangyu
 * @Description TODO
 * @Date 2023/8/10
 **/
@Component
public class SchoolService {

    @Autowired
    private SchoolMapper schoolMapper;

    @RedisCache(prefix = "school", bizNo = "schoolId", parseClass = School.class, expireTime = 120)
    public School getSchoolInfo(Long schoolId){
        return schoolMapper.selectById(schoolId);
    }
}
