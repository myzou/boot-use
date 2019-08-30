package com.example.boot.springdata.repository;

import com.example.boot.springdata.domain.Employee;
import com.example.boot.springdata.service.EmployeeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class EmployeeCrudRepositoryTest {

    private ApplicationContext ctx = null;

    private EmployeeService employeeService = null;

    @Before
    public void setup() {
        ctx = new ClassPathXmlApplicationContext("beans-new.xml");
        employeeService = ctx.getBean(EmployeeService.class);
        System.out.println("setup");
    }

    @After
    public void tearDown() {
        ctx = null;
        System.out.println("tearDown");
    }

    @Test
    public void save() {
        List<Employee> employees = new ArrayList<>();
        Employee employee = null;
        for (int i = 0; i < 100; i++) {
            employee = new Employee();
            employee.setName("test" + i);
            employee.setAge(i);
            employees.add(employee);
        }
        employeeService.save(employees);
    }
}