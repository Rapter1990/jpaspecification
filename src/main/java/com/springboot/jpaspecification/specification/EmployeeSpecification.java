package com.springboot.jpaspecification.specification;

import com.springboot.jpaspecification.entity.Department;
import com.springboot.jpaspecification.entity.Employee;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * Utility class named {@link EmployeeSpecification} providing factory methods for creating Spring Data JPA specifications
 * for querying and filtering {@link Employee} entities.
 */
@UtilityClass
public class EmployeeSpecification {

    /**
     * Creates a specification to filter employees based on the first name.
     *
     * @param name The first name to filter by.
     * @return The Spring Data JPA specification.
     */
    public static Specification<Employee> hasFirstName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("firstName"), name);
    }

    /**
     * Creates a specification to filter employees based on the last name.
     *
     * @param name The last name to filter by.
     * @return The Spring Data JPA specification.
     */
    public static Specification<Employee> hasLastName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("lastName"), name);
    }

    /**
     * Creates a specification to filter employees based on the department name.
     *
     * @param departmentName The department name to filter by.
     * @return The Spring Data JPA specification.
     */
    public static Specification<Employee> hasCategoryNameLike(String departmentName) {
        return (root, query, criteriaBuilder) -> {
            Join<Employee, Department> departmentJoin = root.join("department");

            return criteriaBuilder.like(departmentJoin.get("name"), departmentName);
        };
    }

    /**
     * Creates a specification to search for employees based on a keyword.
     *
     * @param keyword The keyword to search for in the first name, last name, and company name.
     * @return The Spring Data JPA specification.
     */
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

    /**
     * Creates a specification to filter employees with a specific birthday date.
     *
     * @param date The specific birthday date to filter by.
     * @return The Spring Data JPA specification.
     */
    public static Specification<Employee> birthdayDatedAt(LocalDate date) {
        return (root, query, criteriaBuilder) -> {
            // Truncate the time component of the input date
            //Date truncatedDate = truncateTime(date);

            // Check if the birthdayDate is within the specified day
            return criteriaBuilder.equal(
                    root.get("birthdayDate"), date
            );
        };
    }

    /**
     * Creates a specification to filter employees with a birthday date before a specific date.
     *
     * @param date The upper bound birthday date to filter by.
     * @return The Spring Data JPA specification.
     */
    public static Specification<Employee> birthdayDateBeforeThan(LocalDate date) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThan(root.get("birthdayDate"), date);
    }

    /**
     * Creates a specification to filter employees with a birthday date after a specific date.
     *
     * @param date The lower bound birthday date to filter by.
     * @return The Spring Data JPA specification.
     */
    public static Specification<Employee> birthdayDateAfterThan(LocalDate date) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("birthdayDate"), date);
    }

    /**
     * Creates a specification to filter employees with a birthday date between two specific dates.
     *
     * @param startDate The lower bound birthday date to filter by.
     * @param endDate   The upper bound birthday date to filter by.
     * @return The Spring Data JPA specification.
     */
    public static Specification<Employee> birthdayDateBetween(LocalDate startDate, LocalDate endDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("birthdayDate"), startDate, endDate);
    }

    /**
     * Truncates the time component of a date, setting it to midnight.
     *
     * @param date The date to truncate.
     * @return The truncated date.
     */
    private static Date truncateTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

}
