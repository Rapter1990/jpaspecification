package com.springboot.jpaspecification.repository;

import com.springboot.jpaspecification.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for performing CRUD operations on {@link Department} entities.
 * Extends the Spring Data JPA {@link JpaRepository} interface, providing methods for common data access operations.
 */
public interface DepartmentRepository extends JpaRepository<Department,Long> {

}
