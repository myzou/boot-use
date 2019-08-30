package com.dyz.dao;

import com.dyz.bean.Cat;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author dyz
 * @program boot-use
 * @create 2019-08-09 17:56
 */
@Repository
public class CatDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public Cat selectByCatName(String catName) {
        /*
         * 1、定义一个Sql语句；
         * 2、定义一个RowMapper.
         * 3、执行查询方法.
         */
        String sql = "select *from cat where cat_name=?";

        RowMapper<Cat> rowMapper = new BeanPropertyRowMapper<>(Cat.class);

        return jdbcTemplate.queryForObject(sql, new Object[]{catName}, rowMapper);
    }

}
