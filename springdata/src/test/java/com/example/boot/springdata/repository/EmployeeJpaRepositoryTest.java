package com.example.boot.springdata.repository;

import com.example.boot.springdata.domain.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Optional;

/**
 * @ClassName EmployeeJpaRepositoryTest
 * @Author dyzhan
 * @Date 2019/6/616:09
 * @Version 1.0
 **/
public class EmployeeJpaRepositoryTest {

    private ApplicationContext ctx = null;

    private EmployeeJpaRepository employeeJpaRepository = null;

    @Before
    public void setup() {
        ctx = new ClassPathXmlApplicationContext("beans-new.xml");
        employeeJpaRepository = ctx.getBean(EmployeeJpaRepository.class);
        System.out.println("setup");
    }

    @After
    public void tearDown() {
        ctx = null;
        System.out.println("tearDown");
    }

    @Test
    public void testFind() {
        Optional<Employee> employee = employeeJpaRepository.findById(1);
        System.out.println(employee.get().getName());
        System.out.println(employeeJpaRepository.existsById(22));
        System.out.println(employeeJpaRepository.count());
    }

}
