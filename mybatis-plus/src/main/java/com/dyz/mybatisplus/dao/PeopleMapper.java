package com.dyz.mybatisplus.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyz.mybatisplus.entity.People;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author dyz
 * @create 2019-07-08 21:40
 */
public interface PeopleMapper extends BaseMapper<People> {

    /**
     * 自定义sql查询，注解
     *
     * @param wrapper wrapper
     * @return list
     */
    @Select("select * from people ${ew.customSqlSegment}")
    List<People> selectCustomize(@Param(Constants.WRAPPER) Wrapper<People> wrapper);

    /**
     * 自定义sql查询,xml配置
     *
     * @param wrapper wrapper
     * @return list
     */
    List<People> selectCustomize2(@Param(Constants.WRAPPER) Wrapper<People> wrapper);

    /**
     * 自定义分页查询
     *
     * @param page    page
     * @param wrapper wrapper
     * @return IPage
     */
    IPage<People> selectPeoelePage(Page<People> page, @Param(Constants.WRAPPER) Wrapper<People> wrapper);
}
