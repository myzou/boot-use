package com.example.boot.springdata.dao;

import com.example.boot.springdata.domain.Student;
import org.junit.Test;

import java.util.List;

public class StudentDAOImplTest {

    @Test
    public void testQuery() {
        StudentDAO studentDAO = new StudentDAOJdbcImpl();
        List<Student> students = studentDAO.query();

        for (Student student : students) {
            System.out.println("id:" + student.getId()
                    + " , name:" + student.getName()
                    + ", age:" + student.getAge());
        }
    }

    @Test
    public void testSave() {
        StudentDAO studentDAO = new StudentDAOJdbcImpl();
        Student student = new Student();
        student.setName("test");
        student.setAge(30);

        studentDAO.save(student);
    }

}
