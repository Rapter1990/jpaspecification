package com.springboot.jpaspecification.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entity class named {@link Employee} representing an employee.
 * Extends the {@link BaseEntity} class to inherit common fields for tracking creation and update timestamps.
 */
@Data
@Entity
@Table(name = "EMPLOYEES")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee extends BaseEntity {

    /**
     * Unique identifier for the employee.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    /**
     * First name of the employee.
     */
    @Column(name = "FIRST_NAME")
    private String firstName;

    /**
     * Last name of the employee.
     */
    @Column(name = "LAST_NAME")
    private String lastName;

    /**
     * Birthday date of the employee.
     */
    @Column(name = "BIRTHDAY_DATE")
    private LocalDate birthdayDate;

    /**
     * Age of the employee.
     */
    @Column(name = "AGE")
    private Integer age;

    /**
     * Company name of the employee.
     */
    @Column(name = "COMPANY_NAME")
    private String companyName;

    /**
     * Department to which the employee belongs. Many employees can belong to one department.
     */
    @ManyToOne
    private Department department;

}
