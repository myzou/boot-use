package com.example.boot.springdata.repository;

import com.example.boot.springdata.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author dyzhan
 * @Description //TODO
 * @Date 16:28 2019/6/6
 **/
public interface EmployeeJpaSpecificationExecutor extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {
}
