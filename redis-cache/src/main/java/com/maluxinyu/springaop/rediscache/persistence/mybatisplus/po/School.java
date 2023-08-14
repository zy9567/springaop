package com.maluxinyu.springaop.rediscache.persistence.mybatisplus.po;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @ClassName School
 * @Author zhangyu
 * @Description TODO
 * @Date 2023/8/10
 **/
@Data
@TableName("school")
public class School implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String address;

    private LocalDateTime createDt;

    private LocalDateTime updateDt;

}
