package com.springboot.jpaspecification.repository;

import com.springboot.jpaspecification.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

}
