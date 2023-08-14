package com.maluxinyu.springaop.rediscache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.maluxinyu.springaop.rediscache.persistence.mybatisplus.mapper.SchoolMapper;
import com.maluxinyu.springaop.rediscache.persistence.mybatisplus.po.School;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName RedisCacheApplicationTest
 * @Author zhangyu
 * @Description TODO
 * @Date 2023/8/10
 **/
@SpringBootTest
@Slf4j
public class RedisCacheApplicationTest {

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void insertSchoolInfo() {
        School school = new School();
        school.setAddress("上海");
        school.setName("复旦");
        schoolMapper.insert(school);
    }

    @Test
    public void getSchoolInfo() {
        School school = schoolMapper.selectById(150);
        log.info("school = {}", school);
    }

    @Test
    public void getSchoolInfoByRequest() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://127.0.0.1:8080/school/getById";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("schoolId", "150");
        String result = restTemplate.getForObject(builder.toUriString(), String.class);

        System.out.println(result);
    }



}
