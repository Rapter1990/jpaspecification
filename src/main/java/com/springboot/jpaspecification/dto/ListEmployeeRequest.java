package com.springboot.jpaspecification.dto;

import com.springboot.jpaspecification.entity.Employee;
import com.springboot.jpaspecification.specification.EmployeeSpecification;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class ListEmployeeRequest implements Filterable<Employee> {

    private Filter filter;

    @Getter
    @Setter
    private static class Filter {

        private String keyword;
        private String firstName;
        private String lastName;


        private String departmentName;
        private BirthdayDate birthdayDate;

        @Getter
        @Setter
        private static class BirthdayDate {

            private Date birthdayDateFirst;

            private Date birthdayDateSecond;
            private DateComparison dateComparison;

            private enum DateComparison {
                AT,
                AFTER,
                BEFORE,
                BETWEEN
            }
        }

    }


    @Override
    public Specification<Employee> toSpecification() {

        Specification<Employee> specification = Specification.where(null);

        if (filter.keyword != null) {
            specification = specification.and(EmployeeSpecification.search(filter.keyword));
        }

        if (filter.firstName != null) {
            specification = specification.and(EmployeeSpecification.hasFirstName(filter.firstName));
        }

        if (filter.lastName != null) {
            specification = specification.and(EmployeeSpecification.hasFirstName(filter.lastName));
        }

        if (!CollectionUtils.isEmpty(Collections.singleton(filter.departmentName))) {
            specification = specification.and(EmployeeSpecification.hasCategoryNameLike(filter.departmentName));
        }

        if (filter.birthdayDate != null && filter.birthdayDate.birthdayDateFirst != null && filter.birthdayDate.dateComparison != null) {
            Date date = filter.birthdayDate.birthdayDateFirst;
            Filter.BirthdayDate.DateComparison dateComparison = filter.birthdayDate.dateComparison;

            specification = switch (dateComparison) {
                case AT -> specification.and(EmployeeSpecification.birthdayDatedAt(date));
                case AFTER -> specification.and(EmployeeSpecification.birthdayDateBeforeThan(date));
                case BEFORE -> specification.and(EmployeeSpecification.birthdayDateAfterThan(date));
                case BETWEEN -> {
                    Date startDate = filter.birthdayDate.birthdayDateFirst;
                    Date endDate = filter.birthdayDate.birthdayDateSecond;
                    yield specification.and(EmployeeSpecification.birthdayDateBetween(startDate, endDate));
                }
                default -> specification;
            };
        }

        return specification;
    }

}
