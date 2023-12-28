package com.springboot.jpaspecification.controller;

import com.springboot.jpaspecification.dto.ListEmployeeRequest;
import com.springboot.jpaspecification.entity.Employee;
import com.springboot.jpaspecification.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller responsible for managing employee-related HTTP requests.
 * This controller exposes endpoints to interact with employee data, facilitating retrieval based on specified criteria.
 * Endpoints include methods for retrieving a list of employees.
 * The controller named {@link EmployeeController} is designed to work in conjunction with the {@link EmployeeService} for handling business logic.
 */
@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * Handles HTTP GET requests to retrieve a list of employees based on the specified criteria.
     *
     * @param listProductRequest The request object containing criteria for filtering employees.
     * @return ResponseEntity with a list of Employee objects if successful, or an appropriate error response.
     */
    @GetMapping("/getEmployees")
    public ResponseEntity<List<Employee>> getEmployees(@RequestBody ListEmployeeRequest listProductRequest) {
        return ResponseEntity.ok(employeeService.getEmployees(listProductRequest));
    }

}
