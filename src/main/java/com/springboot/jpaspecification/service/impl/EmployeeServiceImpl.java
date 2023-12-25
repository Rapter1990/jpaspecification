package com.springboot.jpaspecification.service.impl;

import com.springboot.jpaspecification.dto.ListEmployeeRequest;
import com.springboot.jpaspecification.entity.Employee;
import com.springboot.jpaspecification.repository.EmployeeRepository;
import com.springboot.jpaspecification.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getEmployees(ListEmployeeRequest listEmployeeRequest) {
        return employeeRepository.findAll(listEmployeeRequest.toSpecification());
    }
}
