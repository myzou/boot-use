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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dyz
 * @create 2019-07-09 22:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PeopleSelectMapperTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private PeopleMapper peopleMapper;

    @Test
    public void selectById() {
        People people = peopleMapper.selectById(1087982257332887555L);
        logger.info(" selectById - " + people);
    }

    @Test
    public void selectBatchIds() {
        List<People> peopleList = peopleMapper.selectBatchIds(Arrays.asList(1087982257332887555L,1087982257332887554L));
        Assert.assertEquals(2, peopleList.size());
        peopleList.forEach(System.out::println);
    }

    @Test
    public void selectByMap() {
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("name","dyz");
        columnMap.put("manager_id",null);
        List<People> peopleList = peopleMapper.selectByMap(columnMap);
        Assert.assertEquals(1, peopleList.size());
        peopleList.forEach(System.out::println);
    }

}
