package com.dyz.mybatisplus.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyz.mybatisplus.entity.People;
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
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PeopleSelectByWrapperMapperTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private PeopleMapper peopleMapper;

    @Test
    public void selectOne() {
        /*
         * 方式二选一
         */
        QueryWrapper<People> queryWrapper = new QueryWrapper<People>();
        QueryWrapper<People> queryWrapper1 = Wrappers.<People>query();
        queryWrapper.like("name", "d").between("age", 10, 20).isNotNull("email");
        /*
         * selectOne()只能是返回one；
         * 否则报错Caused by: org.apache.ibatis.exceptions.TooManyResultsException:
         * Expected one result (or null) to be returned by selectOne(), but found: 2
         */
        People people = peopleMapper.selectOne(queryWrapper);
        logger.info(" selectOne - " + people);
    }

    @Test
    public void selectList() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "雨").lt("age", 100);

        List<People> peopleList = peopleMapper.selectList(queryWrapper);
        peopleList.forEach(System.out::println);
    }

    @Test
    public void selectCount() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "d").or().ge("age", 40).orderByDesc("age").orderByAsc("id");

        int result = peopleMapper.selectCount(queryWrapper);
        logger.info(" selectCount - " + result);
    }

    @Test
    public void selectObjs() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}", "2019-01-14")
                .inSql("manager_id", "select id from people where name like '王%'");

        List<Object> list = peopleMapper.selectObjs(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void selectObjs2() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        /*
         * 引发sql注入问题
         */
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d')='2019-01-14' or true or true")
                .inSql("manager_id", "select id from people where name like '王%'");

        List<Object> list = peopleMapper.selectObjs(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void selectMaps() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        /*
        SELECT id,name,age,email,manager_id,create_time FROM people WHERE name LIKE ? AND ( age < ? OR email IS NOT NULL )
         */
        queryWrapper.likeRight("name", "王").and(wq -> wq.lt("age", 40).or().isNotNull("email"));

        List<Map<String, Object>> list = peopleMapper.selectMaps(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void selectPage() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        /*
         */
        queryWrapper.likeRight("name", "王").or(wq -> wq.lt("age", 40).gt("age", 20).isNotNull("email"));

        int result = peopleMapper.selectCount(queryWrapper);

        /*
        SELECT COUNT(1) FROM people WHERE name LIKE ? OR (age < ? AND age > ? AND email IS NOT NULL) ;
        SELECT id,name,age,email,manager_id,create_time FROM people WHERE name LIKE ? OR
            ( age < ? AND age > ? AND email IS NOT NULL ) LIMIT ?,? ;
         */
        Page<People> page = new Page<People>(1, 2);
        IPage<People> peopleIPage = peopleMapper.selectPage(page, queryWrapper);
        logger.info(" selectPage - " + peopleIPage);
        logger.info(" getPages - " + peopleIPage.getPages());
        logger.info(" getTotal - " + peopleIPage.getTotal());
        List<People> peopleList = peopleIPage.getRecords();
        peopleList.forEach(System.out::println);
    }

    @Test
    public void selectMapsPage() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        /*
        SELECT COUNT(1) FROM people WHERE (age < ? OR email IS NOT NULL) AND name LIKE ? ;
        SELECT id,name,age,email,manager_id,create_time FROM people WHERE
            ( age < ? OR email IS NOT NULL ) AND name LIKE ? LIMIT ?,? ;
         */
        queryWrapper.nested(wq -> wq.lt("age", 40).or().isNotNull("email")).likeRight("name", "王");
        Page<People> page = new Page<People>(1, 2);
        /*
        不查总记录数，只有一条sql语句
        SELECT id,name,age,email,manager_id,create_time FROM people WHERE ( age < ? OR email IS NOT NULL ) AND name LIKE ? LIMIT ?,? ;
         */
        Page<People> page2 = new Page<People>(1, 2, false);
        IPage<Map<String, Object>> list = peopleMapper.selectMapsPage(page2, queryWrapper);
        logger.info(" selectMapsPage - " + list.getRecords());
        logger.info(" selectMapsPage - " + list);
        logger.info(" getPages - " + list.getPages());
        logger.info(" getTotal - " + list.getTotal());
        List<Map<String, Object>> peopleList = list.getRecords();
        peopleList.forEach(System.out::println);
    }

    /**
     * 自定义分页查询(解决多表分页)
     */
    @Test
    public void selectMyPage() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "d");
        /*
         * SELECT COUNT(1) FROM people WHERE name LIKE ? ;
         * select * from people WHERE name LIKE ? LIMIT ?,? ;
         */
        Page<People> page = new Page<People>(1, 2);
        IPage<People> peopleIPage = peopleMapper.selectPeoelePage(page, queryWrapper);
        logger.info(" selectPage - " + peopleIPage);
        logger.info(" getPages - " + peopleIPage.getPages());
        logger.info(" getTotal - " + peopleIPage.getTotal());
        List<People> peopleList = peopleIPage.getRecords();
        peopleList.forEach(System.out::println);
    }

    @Test
    public void selectMapsPage2() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        /*
        SELECT id,name,age,email,manager_id,create_time FROM people WHERE age IN (?,?,?,?)
         */
        queryWrapper.in("age", Arrays.asList(30, 31, 34, 18));
        IPage<People> page = new Page<People>(1, 2);
        IPage<Map<String, Object>> list = peopleMapper.selectMapsPage(page, queryWrapper);
        logger.info(" selectMapsPage - " + list.getRecords());
    }

    @Test
    public void selectList2() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        /*
        SELECT id,name,age,email,manager_id,create_time FROM people WHERE age IN (?,?,?,?) limit 1
         */
        queryWrapper.in("age", Arrays.asList(30, 31, 34, 18)).last("limit 1");
        List<People> peopleList = peopleMapper.selectList(queryWrapper);
        peopleList.forEach(System.out::print);
    }

    @Test
    public void selectList3() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        /*
        SELECT id,name,age FROM people WHERE age IN (?,?,?,?) limit 1
         */
        queryWrapper.select("id", "name", "age").in("age", Arrays.asList(30, 31, 34, 18)).last("limit 1");
        List<People> peopleList = peopleMapper.selectList(queryWrapper);
        peopleList.forEach(System.out::print);
    }

    @Test
    public void selectList4() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        /*
        SELECT id,name,age,email FROM people WHERE age IN (?,?,?,?) limit 1
         */
        queryWrapper.in("age", Arrays.asList(30, 31, 34, 18)).last("limit 1")
                .select(People.class, info -> !info.getColumn().equals("create_time") && !info.getColumn().equals("manager_id"));
        List<People> peopleList = peopleMapper.selectList(queryWrapper);
        peopleList.forEach(System.out::print);
    }

    @Test
    public void testCondition() {
        String name = "d";
        String email = "";
        condition(name, email);
    }

    private void condition(String name, String email) {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (StringUtils.isNotEmpty(email)) {
            queryWrapper.like("email", email);
        }
        /*
        SELECT id,name,age,email,manager_id,create_time FROM people WHERE name LIKE ?
         */
        List<People> peopleList = peopleMapper.selectList(queryWrapper);
        peopleList.forEach(System.out::print);
    }

    @Test
    public void testCondition2() {
        String name = "d";
        String email = "";
        condition2(name, email);
    }

    private void condition2(String name, String email) {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name), "name", name)
                .like(StringUtils.isNotEmpty(email), "email", email);
        /*
        SELECT id,name,age,email,manager_id,create_time FROM people WHERE name LIKE ?
         */
        List<People> peopleList = peopleMapper.selectList(queryWrapper);
        peopleList.forEach(System.out::print);
    }

    @Test
    public void selectByWrapperAllEq() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "dyz");
        map.put("age", null);
        queryWrapper.allEq(map);
        /*
        SELECT id,name,age,email,manager_id,create_time FROM people WHERE name = ? AND age IS NULL
         */
        List<People> peopleList = peopleMapper.selectList(queryWrapper);
        peopleList.forEach(System.out::print);
    }

    @Test
    public void selectByWrapperAllEq2() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "dyz");
        map.put("age", null);
        queryWrapper.allEq(map, false);
        /*
        SELECT id,name,age,email,manager_id,create_time FROM people WHERE name = ?
         */
        List<People> peopleList = peopleMapper.selectList(queryWrapper);
        peopleList.forEach(System.out::print);
    }

    @Test
    public void selectByWrapperAllEq3() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "dyz");
        map.put("age", null);
        queryWrapper.allEq((k, v) -> !k.equals("name"), map);
        /*
        SELECT id,name,age,email,manager_id,create_time FROM people WHERE age IS NULL
         */
        List<People> peopleList = peopleMapper.selectList(queryWrapper);
        peopleList.forEach(System.out::print);
    }

}
