package com.dyz.mybatisplus.dao;

import com.dyz.mybatisplus.entity.People;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author dyz
 * @create 2019-07-08 21:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PeopleMapperTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private PeopleMapper peopleMapper;

    @Test
    public void select() {
        List<People> peopleList = peopleMapper.selectList(null);
        Assert.assertEquals(10, peopleList.size());
        peopleList.forEach(System.out::println);
    }

    @Test
    public void insert() {
        People people = new People(1087982257332887555L, "dyz-09@TableField", 18, "dyz@163.com", null, LocalDateTime.now(),"备注");
        int result = peopleMapper.insert(people);
        Assert.assertEquals(1, result);
        logger.info(" PeopleMapperTest insert -- " + result);
    }

}