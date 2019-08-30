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

public class EmployeePagingAndSortingRepositoryTest {

    private ApplicationContext ctx = null;

    private EmployeePagingAndSortingRepository employeePagingAndSortingRepository = null;

    @Before
    public void setup() {
        ctx = new ClassPathXmlApplicationContext("beans-new.xml");
        employeePagingAndSortingRepository = ctx.getBean(EmployeePagingAndSortingRepository.class);
        System.out.println("setup");
    }

    @After
    public void tearDown() {
        ctx = null;
        System.out.println("tearDown");
    }

    @Test
    public void testPageAndSort() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
        Page<Employee> employeePage = employeePagingAndSortingRepository.findAll(pageable);
        System.out.println(employeePage.getTotalPages());
        System.out.println(employeePage.getTotalElements());
        System.out.println(employeePage.getNumber());
        System.out.println(employeePage.getContent());
        System.out.println(employeePage.getNumberOfElements());
    }
}