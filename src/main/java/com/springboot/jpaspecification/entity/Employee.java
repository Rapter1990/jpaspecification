package com.springboot.jpaspecification.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "EMPLOYEES")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "BIRTHDAY_DATE")
    private Date birthdayDate;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @ManyToMany
    private Set<Department> departments;

}
