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

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/getEmployees")
    public ResponseEntity<List<Employee>> getEmployees(@RequestBody ListEmployeeRequest listProductRequest) {
        return ResponseEntity.ok(employeeService.getEmployees(listProductRequest));
    }
}
