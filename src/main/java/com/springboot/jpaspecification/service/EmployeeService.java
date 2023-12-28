package com.springboot.jpaspecification.service;

import com.springboot.jpaspecification.dto.ListEmployeeRequest;
import com.springboot.jpaspecification.entity.Employee;

import java.util.List;

/**
 * Service interface {@link EmployeeService} for managing and retrieving employee-related data.
 */
public interface EmployeeService {

    /**
     * Retrieves a list of employees based on the provided criteria in the {@link ListEmployeeRequest} object.
     *
     * @param listEmployeeRequest The request object containing criteria for filtering employees.
     * @return A list of {@link Employee} objects that match the specified criteria.
     */
    List<Employee> getEmployees(ListEmployeeRequest listEmployeeRequest);

}
