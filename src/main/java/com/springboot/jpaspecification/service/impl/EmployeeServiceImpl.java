package com.springboot.jpaspecification.service.impl;

import com.springboot.jpaspecification.dto.ListEmployeeRequest;
import com.springboot.jpaspecification.entity.Employee;
import com.springboot.jpaspecification.repository.EmployeeRepository;
import com.springboot.jpaspecification.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the {@link EmployeeService} interface providing business logic
 * for managing and retrieving employee-related data.
 */
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    /**
     * Retrieves a list of employees based on the provided criteria in the {@link ListEmployeeRequest} object.
     *
     * @param listEmployeeRequest The request object containing criteria for filtering employees.
     * @return A list of {@link Employee} objects that match the specified criteria.
     */
    @Override
    public List<Employee> getEmployees(ListEmployeeRequest listEmployeeRequest) {
        return employeeRepository.findAll(listEmployeeRequest.toSpecification());
    }

}
