package com.dyz.mybatisplus.dao;

import com.dyz.mybatisplus.entity.People;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @author dyz
 * @create 2019-07-13 18:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ARTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void insert() {
        People people = People.builder().id(1087982257332887559L).name("AR").age(19).email("792171677@qq.com")
                .createTime(LocalDateTime.now()).build();
        /*
        INSERT INTO people ( id, name, age, email, create_time ) VALUES ( ?, ?, ?, ?, ? )
         */
        boolean insert = people.insert();
        logger.info(" - AR Insert - " + insert);
    }

    @Test
    public void selectById() {
        People people = People.builder().build();
        /*
        SELECT id,name,age,email,manager_id,create_time FROM people WHERE id=?
         */
        People selectPeople = people.selectById(1087982257332887559L);
        /*
        false
        不是同一个对象
         */
        System.out.println(people == selectPeople);
        logger.info(" - AR selectById - " + selectPeople);
    }

    @Test
    public void updateById() {
        People people = People.builder().id(1087982257332887559L).name("AR").age(19).email("792171677@qq.com")
                .createTime(LocalDateTime.now()).build();
        /*
        UPDATE people SET name=?, age=?, email=?, create_time=? WHERE id=?
         */
        boolean update = people.updateById();
        logger.info(" - AR updateById - " + update);
    }

    @Test
    public void deleteById() {
        People people = People.builder().id(1087982257332887559L).build();
        /*
        DELETE FROM people WHERE id=?
         */
        boolean delete = people.deleteById();
        logger.info(" - AR deleteById - " + delete);
    }

    @Test
    public void insertOrUpdate() {
        People people = People.builder().id(1087982257332887559L).name("AR").age(19).email("792171677@qq.com")
                .createTime(LocalDateTime.now()).build();
        /*
        执行两条语句：
        SELECT id,name,age,email,manager_id,create_time FROM people WHERE id=? ;
        INSERT INTO people ( id, name, age, email, create_time ) VALUES ( ?, ?, ?, ?, ? ) ;
         */
        boolean insertOrUpdate = people.insertOrUpdate();
        logger.info(" - AR insertOrUpdate - " + insertOrUpdate);
    }

    @Test
    public void insertAutoId() {
        People people = People.builder().name("ARAuto").age(19).email("792171677@qq.com")
                .createTime(LocalDateTime.now()).build();
        /*
        INSERT INTO people ( id, name, age, email, create_time ) VALUES ( ?, ?, ?, ?, ? )
         */
        boolean insert = people.insert();
        logger.info(" - AR insertAutoId - " + insert);
        logger.info(" - AR insertAutoId - " + people.getId());
    }

}
