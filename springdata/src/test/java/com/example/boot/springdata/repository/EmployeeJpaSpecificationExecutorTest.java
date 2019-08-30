package com.example.boot.springdata.repository;


import com.example.boot.springdata.domain.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

/**
 * @ClassName EmployeeJpaSpecificationExecutorTest
 * @Author dyzhan
 * @Date 2019/6/616:29
 * @Version 1.0
 **/
public class EmployeeJpaSpecificationExecutorTest {

    private ApplicationContext ctx = null;

    private EmployeeJpaSpecificationExecutor employeeJpaSpecificationExecutor = null;

    @Before
    public void setup() {
        ctx = new ClassPathXmlApplicationContext("beans-new.xml");
        employeeJpaSpecificationExecutor = ctx.getBean(EmployeeJpaSpecificationExecutor.class);
        System.out.println("setup");
    }

    @After
    public void tearDown() {
        ctx = null;
        System.out.println("tearDown");
    }

    @Test
    public void testPageAndSort() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));

        Specification<Employee> specification = (Specification<Employee>) (root, criteriaQuery, criteriaBuilder) -> {
            Path path = root.get("age");
            return criteriaBuilder.gt(path, 50);
        };

        Page<Employee> employeePage = employeeJpaSpecificationExecutor.findAll(specification, pageable);

        System.out.println(employeePage.getTotalPages());
        System.out.println(employeePage.getTotalElements());
        System.out.println(employeePage.getNumber());
        System.out.println(employeePage.getContent());
        System.out.println(employeePage.getNumberOfElements());
    }

}
