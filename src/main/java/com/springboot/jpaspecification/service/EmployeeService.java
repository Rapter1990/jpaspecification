package com.springboot.jpaspecification.service;

import com.springboot.jpaspecification.dto.ListEmployeeRequest;
import com.springboot.jpaspecification.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployees(ListEmployeeRequest listEmployeeRequest);
}
