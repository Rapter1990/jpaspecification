package com.springboot.jpaspecification.repository;

import com.springboot.jpaspecification.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  EmployeeRepository extends JpaRepository<Employee,Long> {

}
