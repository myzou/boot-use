package com.example.boot.springdata.repository;

import com.example.boot.springdata.domain.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @Author dyzhan
 * @Description //TODO
 * @Date 14:03 2019/6/6
 **/
public interface EmployeePagingAndSortingRepository extends PagingAndSortingRepository<Employee, Integer> {
}
