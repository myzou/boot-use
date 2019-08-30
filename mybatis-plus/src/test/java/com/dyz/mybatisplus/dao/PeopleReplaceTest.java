package com.dyz.mybatisplus.dao;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.dyz.mybatisplus.entity.People;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author dyz
 * @create 2019-07-13 16:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PeopleReplaceTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private PeopleMapper peopleMapper;

    @Test
    public void updateById() {
        People people = People.builder().id(1087982257332887550L).age(19).email("792171677@qq.com").build();
        /*
        Preparing: UPDATE people SET age=?, email=? WHERE id=?
         */
        int rows = peopleMapper.updateById(people);
        Assert.assertEquals(1, rows);
        logger.info(" - updateById - " + rows);
    }

    @Test
    public void update() {
        UpdateWrapper<People> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", 1087982257332887550L);
        People people = People.builder().id(1087982257332887550L).age(20).email("123456@qq.com").build();
        /*
        UPDATE people SET age=?, email=? WHERE id = ?
         */
        int rows = peopleMapper.update(people, updateWrapper);
        Assert.assertEquals(1, rows);
        logger.info(" - update - " + rows);
    }

    @Test
    public void update2() {
        People wherePeople = new People();
        wherePeople.setAge(20);

        UpdateWrapper<People> updateWrapper = new UpdateWrapper<>(wherePeople);
        updateWrapper.eq("id", 1087982257332887550L);
        People people = People.builder().email("1234567@qq.com").build();
        /*
        UPDATE people SET email=? WHERE age=? AND id = ?
         */
        int rows = peopleMapper.update(people, updateWrapper);
        Assert.assertEquals(1, rows);
        logger.info(" - update - " + rows);
    }

    /**
     * 修改少量列
     */
    @Test
    public void update3() {
        UpdateWrapper<People> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", 1087982257332887550L).set("age", 21);
        /*
        UPDATE people SET age=? WHERE id = ?
         */
        int rows = peopleMapper.update(null, updateWrapper);
        Assert.assertEquals(1, rows);
        logger.info(" - update - " + rows);
    }

    @Test
    public void updateByWrapperLambda() {
        LambdaUpdateWrapper<People> lambdaUpdateWrapper = Wrappers.<People>lambdaUpdate();
        lambdaUpdateWrapper.eq(People::getName, "dyz").eq(People::getAge, 21).set(People::getAge, 20);
        /*
        UPDATE people SET age=? WHERE name = ? AND age = ?
         */
        int rows = peopleMapper.update(null, lambdaUpdateWrapper);
        Assert.assertEquals(1, rows);
        logger.info(" - update - " + rows);
    }

    @Test
    public void updateByWrapperLambdaChain() {
        boolean update = new LambdaUpdateChainWrapper<People>(peopleMapper)
                .eq(People::getName, "dyz").eq(People::getAge, 20).set(People::getAge, 19).update();
        /*
        UPDATE people SET age=? WHERE name = ? AND age = ?
         */
        logger.info(" - updateByWrapperLambdaChain - " + update);
    }

}
