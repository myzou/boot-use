package com.dyz.mybatisplus.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.dyz.mybatisplus.entity.People;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author dyz
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PeopleSelectMapperOtherTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private PeopleMapper peopleMapper;

    @Test
    public void selectMaps() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王").lt("age", 40).select("id", "name");
        /*
        selectMaps使用场景 查询出其中几列（结果“{name=王天风, id=1088248166370832385}”，不存在null）
         */
        List<Map<String, Object>> list = peopleMapper.selectMaps(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void selectMaps2() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("avg(age) avg_age", "min(age) min_age", "max(age) max_age").groupBy("manager_id")
                .having("sum(age) < {0}", 500);
        /*
        SELECT avg(age) avg_age,min(age) min_age,max(age) max_age FROM people GROUP BY manager_id HAVING sum(age) < ?
         */
        List<Map<String, Object>> list = peopleMapper.selectMaps(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void selectObjs() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王").lt("age", 40).select("id", "name");
        /*
        注意：是返回第一个字段的值. 如：
            DEBUG==>  Preparing: SELECT id,name FROM people WHERE name LIKE ? AND age < ?
            DEBUG==> Parameters: 王%(String), 40(Integer)
            TRACE<==    Columns: id, name
            TRACE<==        Row: 1088248166370832385, 王天风
            DEBUG<==      Total: 1
            1088248166370832385
        查询两个字段，实际返回一个字段
         */
        List<Object> list = peopleMapper.selectObjs(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void selectCount() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王").lt("age", 40);
        /*
        不能加select列
        SELECT COUNT( 1 ) FROM people WHERE name LIKE ? AND age < ?
         */
        Integer count = peopleMapper.selectCount(queryWrapper);
        logger.info(" selectCount " + count);
    }

    @Test
    public void selectOne() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王").lt("age", 40).select("id", "name");
        /*
        返回的结果大于1条，则报错
         */
        People people = peopleMapper.selectOne(queryWrapper);
        logger.info(" selectOne " + people);
    }

    @Test
    public void selectLambda() {
        /*
        实例化方法 三选一
         */
        LambdaQueryWrapper<People> lambda = new QueryWrapper<People>().lambda();
        LambdaQueryWrapper<People> peopleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<People> peopleLambdaQueryWrapper1 = Wrappers.<People>lambdaQuery();
        /*
        SELECT id,name,age,email,manager_id,create_time FROM people WHERE name LIKE ? AND age < ?
         */
        lambda.like(People::getName, "d").lt(People::getAge, 40);

        List<People> list = peopleMapper.selectList(lambda);
        list.forEach(System.out::println);
    }

    @Test
    public void selectLambda2() {
        LambdaQueryWrapper<People> peopleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        /*
        SELECT id,name,age,email,manager_id,create_time FROM people WHERE name LIKE ? AND ( age < ? OR email IS NOT NULL )
         */
        peopleLambdaQueryWrapper.likeRight(People::getName, "d").and(lqw -> lqw.lt(People::getAge, 40).or()
                .isNotNull(People::getEmail));

        List<People> list = peopleMapper.selectList(peopleLambdaQueryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void selectLambda3() {
        List<People> peopleList = new LambdaQueryChainWrapper<People>(peopleMapper).like(People::getName, "d")
                .ge(People::getAge, 20).list();
        /*
        SELECT id,name,age,email,manager_id,create_time FROM people WHERE name LIKE ? AND age >= ?
         */
        peopleList.forEach(System.out::println);
    }

    /**
     * 自定义sql查询 测试
     */
    @Test
    public void selectCustomize() {
        LambdaQueryWrapper<People> peopleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        /*
        select * from people WHERE name LIKE ? AND ( age < ? OR email IS NOT NULL )
         */
        peopleLambdaQueryWrapper.likeRight(People::getName, "d").and(lqw -> lqw.lt(People::getAge, 40).or()
                .isNotNull(People::getEmail));

        List<People> list = peopleMapper.selectCustomize(peopleLambdaQueryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 自定义sql查询 测试xml
     */
    @Test
    public void selectCustomize2() {
        LambdaQueryWrapper<People> peopleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        /*
        select * from people WHERE name LIKE ? AND ( age < ? )
         */
        peopleLambdaQueryWrapper.likeRight(People::getName, "dyz").and(lqw -> lqw.lt(People::getAge, 30));

        List<People> list = peopleMapper.selectCustomize2(peopleLambdaQueryWrapper);
        list.forEach(System.out::println);
    }

}
