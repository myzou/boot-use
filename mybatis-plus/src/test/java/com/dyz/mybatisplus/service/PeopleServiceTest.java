package com.dyz.mybatisplus.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dyz.mybatisplus.entity.People;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author dyz
 * @create 2019-07-13 23:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PeopleServiceTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private IPeopleService peopleService;

    @Test
    public void getOne() {
        /*
        SELECT id,name,age,email,manager_id,create_time FROM people WHERE age > ? ;
        查询出多条-报错
        (Caused by: org.apache.ibatis.exceptions.TooManyResultsException: Expected one result (or null) to be returned by selectOne(), but found: 2)
         */
        People people = peopleService.getOne(Wrappers.<People>lambdaQuery().gt(People::getAge, 20));
        logger.info(" IService getOne - " + people);
    }

    @Test
    public void getOne2() {
        /*
        SELECT id,name,age,email,manager_id,create_time FROM people WHERE age > ?
        查询出多条-警告
        (WARNWarn: execute Method There are  2 results.)
         */
        People people = peopleService.getOne(Wrappers.<People>lambdaQuery().gt(People::getAge, 20), false);
        logger.info(" IService getOne2 - " + people);
    }

    @Test
    public void saveBatch() {
        People people1 = People.builder().name("batch1").age(19).email("792171677@qq.com").build();
        People people2 = People.builder().name("batch2").age(20).email("792171678@qq.com").build();
        /*
        DEBUG==>  Preparing: INSERT INTO people ( name, age, email ) VALUES ( ?, ?, ? )
        DEBUG==> Parameters: batch1(String), 19(Integer), 792171677@qq.com(String)
        DEBUG==> Parameters: batch2(String), 20(Integer), 792171678@qq.com(String)
         */
        List<People> peopleList = Arrays.asList(people1, people2);
        boolean saveBatch = peopleService.saveBatch(peopleList);
        logger.info(" IService saveBatch - " + saveBatch);
    }

    @Test
    public void chain() {
        /*
        SELECT id,name,age,email,manager_id,create_time FROM people WHERE age > ? AND name LIKE ?
         */
        List<People> peopleList = peopleService.lambdaQuery().gt(People::getAge, 10).like(People::getName, "d").list();
        peopleList.forEach(System.out::println);
    }

    @Test
    public void chain2() {
        /*
        UPDATE people SET age=? WHERE age = ?
         */
        boolean update = peopleService.lambdaUpdate().eq(People::getAge, 20).set(People::getAge, 22).update();
        logger.info(" lambdaUpdate - " + update);
    }

    @Test
    public void chain3() {
        /*
        DELETE FROM people WHERE age = ?
         */
        boolean remove = peopleService.lambdaUpdate().eq(People::getAge, 22).remove();
        logger.info(" lambdaUpdate - " + remove);
    }

}
