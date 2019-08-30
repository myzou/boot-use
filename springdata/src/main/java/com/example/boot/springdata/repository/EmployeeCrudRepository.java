package com.example.boot.springdata.repository;

import com.example.boot.springdata.domain.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author dyzhan
 * @Description //TODO
 * @Date 13:37 2019/6/6
 **/
public interface EmployeeCrudRepository extends CrudRepository<Employee, Integer> {
}
