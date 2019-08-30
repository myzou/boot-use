package com.example.boot.springdata.repository;

import com.example.boot.springdata.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author dyzhan
 * @Description //TODO
 * @Date 16:08 2019/6/6
 **/
public interface EmployeeJpaRepository extends JpaRepository<Employee, Integer> {
}
