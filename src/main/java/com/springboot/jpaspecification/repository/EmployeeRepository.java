package com.springboot.jpaspecification.repository;

import com.springboot.jpaspecification.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for performing CRUD and specification-based operations on {@link Employee} entities.
 * Extends the Spring Data JPA {@link JpaRepository} interface for common CRUD operations,
 * and the {@link JpaSpecificationExecutor} interface for handling specification-based queries.
 */
public interface EmployeeRepository extends CrudRepository<Employee,Long>, JpaSpecificationExecutor<Employee> {

}
