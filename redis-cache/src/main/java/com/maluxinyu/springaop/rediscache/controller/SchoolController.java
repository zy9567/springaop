package com.maluxinyu.springaop.rediscache.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maluxinyu.springaop.rediscache.persistence.mybatisplus.po.School;
import com.maluxinyu.springaop.rediscache.service.SchoolService;

/**
 * @ClassName SchoolController
 * @Author zhangyu
 * @Description TODO
 * @Date 2023/8/10
 **/
@RestController
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @GetMapping("/school/getById")
    public String getSchool(
            @Param("schoolId") Long schoolId
    ){
        School schoolInfo = schoolService.getSchoolInfo(schoolId);
        return schoolInfo.toString();
    }
}
