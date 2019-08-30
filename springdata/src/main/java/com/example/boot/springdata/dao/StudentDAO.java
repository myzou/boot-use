package com.example.boot.springdata.dao;

import com.example.boot.springdata.domain.Student;

import java.util.List;

/**
 * @Author dyzhan
 * @Description //TODO StudentDAO访问接口
 * @Date 16:54 2019/6/5
 **/
public interface StudentDAO {

    /**
     * 查询所有学生
     *
     * @return 所有学生
     */
    List<Student> query();

    /**
     * 添加一个学生
     *
     * @param student 待添加的学生
     */
    void save(Student student);

}
