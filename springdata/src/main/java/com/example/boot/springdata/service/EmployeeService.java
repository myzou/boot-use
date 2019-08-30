package com.example.boot.springdata.service;

import com.example.boot.springdata.domain.Employee;
import com.example.boot.springdata.repository.EmployeeCrudRepository;
import com.example.boot.springdata.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author dyzhan
 * @Description //TODO
 * @Date 16:53 2019/6/5
 **/
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeCrudRepository employeeCrudRepository;

    @Transactional(rollbackOn = Exception.class)
    public void update(Integer id, Integer age) {
        employeeRepository.update(id, age);
    }

    @Transactional(rollbackOn = Exception.class)
    public void save(List<Employee> employees) {
        employeeCrudRepository.saveAll(employees);
    }

}
