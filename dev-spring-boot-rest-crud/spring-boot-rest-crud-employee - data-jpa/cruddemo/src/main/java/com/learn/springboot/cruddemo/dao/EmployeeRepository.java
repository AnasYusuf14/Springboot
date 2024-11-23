package com.learn.springboot.cruddemo.dao;

import com.learn.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
//    that is no need to write any code and no need impl class
}
