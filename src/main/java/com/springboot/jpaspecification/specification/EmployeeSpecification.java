package com.springboot.jpaspecification.specification;

import com.springboot.jpaspecification.entity.Department;
import com.springboot.jpaspecification.entity.Employee;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

@UtilityClass
public class EmployeeSpecification {

    public static Specification<Employee> hasFirstName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("firstName"), name);
    }

    public static Specification<Employee> hasCategoryNameLike(String departmentName) {
        return (root, query, criteriaBuilder) -> {
            Join<Employee, Department> departmentJoin = root.join("department");

            return criteriaBuilder.like(departmentJoin.get("name"), departmentName);
        };
    }

    public static Specification<Employee> search(String keyword) {
        return (root, query, criteriaBuilder) -> {

            Expression<String> firstNameLowercase = criteriaBuilder.lower(root.get("firstName"));
            Expression<String> lastNameLowercase = criteriaBuilder.lower(root.get("lastName"));
            Expression<String> companyNameLowercase = criteriaBuilder.lower(root.get("companyName"));

            return criteriaBuilder.or(
                    criteriaBuilder.like(firstNameLowercase, "%" + keyword.toLowerCase() + "%"),
                    criteriaBuilder.like(lastNameLowercase, "%" + keyword.toLowerCase() + "%"),
                    criteriaBuilder.like(companyNameLowercase, "%" + keyword.toLowerCase() + "%")
            );
        };
    }

    public static Specification<Employee> birthdayDatedAt(Date date) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("birthdayDate"), date);
    }

    public static Specification<Employee> birthdayDateBeforeThan(Date date) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThan(root.get("birthdayDate"), date);
    }

    public static Specification<Employee> birthdayDateAfterThan(Date date) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("birthdayDate"), date);
    }

    public static Specification<Employee> birthdayDateBetween(Date startDate, Date endDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("birthdayDate"), startDate, endDate);
    }

}
