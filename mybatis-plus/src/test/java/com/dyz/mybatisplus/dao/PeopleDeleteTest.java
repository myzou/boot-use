package com.dyz.mybatisplus.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
import java.util.Map;

/**
 * @author dyz
 * @create 2019-07-13 17:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PeopleDeleteTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private PeopleMapper peopleMapper;

    @Test
    public void deleteById() {
        /*
        DELETE FROM people WHERE id=?
         */
        int rows = peopleMapper.deleteById(1087982257332887554L);
        Assert.assertEquals(1, rows);
        logger.info(" - deleteById - " + rows);
    }

    @Test
    public void deleteByMap() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("id", 1087982257332887555L);
        columnMap.put("age", 18);
        /*
        DELETE FROM people WHERE id = ? AND age = ?
         */
        int rows = peopleMapper.deleteByMap(columnMap);
        Assert.assertEquals(1, rows);
        logger.info(" - deleteByMap - " + rows);
    }

    @Test
    public void deleteBatchIds() {
        /*
        DELETE FROM people WHERE id IN ( ? , ? )
         */
        int rows = peopleMapper.deleteBatchIds(Arrays.asList(1094592041087729666L, 1094590409767661570L));
        Assert.assertEquals(2, rows);
        logger.info(" - deleteBatchIds - " + rows);
    }

    @Test
    public void delete() {
        LambdaQueryWrapper<People> lambdaQueryWrapper = Wrappers.<People>lambdaQuery();
        lambdaQueryWrapper.eq(People::getAge, 28).or().gt(People::getAge, 99);
        /*
        DELETE FROM people WHERE age = ? OR age > ?
         */
        int rows = peopleMapper.delete(lambdaQueryWrapper);
        Assert.assertEquals(1, rows);
        logger.info(" - delete - " + rows);
    }

}
