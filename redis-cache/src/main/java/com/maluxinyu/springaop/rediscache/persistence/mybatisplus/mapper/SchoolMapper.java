package com.maluxinyu.springaop.rediscache.persistence.mybatisplus.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maluxinyu.springaop.rediscache.persistence.mybatisplus.po.School;

/**
 * @InterfaceName SchoolMapper
 * @Author zhangyu
 * @Description TODO
 * @Date 2023/8/10
 **/
@Mapper
public interface SchoolMapper extends BaseMapper<School> {
}
