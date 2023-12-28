package com.springboot.jpaspecification.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class named {@link Department} representing a department.
 * Extends the {@link BaseEntity} class to inherit common fields for tracking creation and update timestamps.
 */
@Data
@Entity
@Table(name = "DEPARTMENTS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department extends BaseEntity {

    /**
     * Unique identifier for the department.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    /**
     * Name of the department.
     */
    @Column(name = "NAME")
    private String name;

}
