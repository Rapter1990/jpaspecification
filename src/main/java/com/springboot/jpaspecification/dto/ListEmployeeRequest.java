package com.springboot.jpaspecification.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.springboot.jpaspecification.entity.Employee;
import com.springboot.jpaspecification.specification.EmployeeSpecification;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
public class ListEmployeeRequest implements Filterable<Employee> {

    private Filter filter;

    @Getter
    @Setter
    public static class Filter {

        private Optional<String> keyword = Optional.empty();
        private Optional<String> firstName = Optional.empty();
        private Optional<String> lastName = Optional.empty();
        private Optional<String> departmentName = Optional.empty();
        private Optional<BirthdayDate> birthdayDate = Optional.empty();

        @Getter
        @Setter
        public static class BirthdayDate {

            private Optional<Date> birthdayDateFirst = Optional.empty();
            private Optional<Date> birthdayDateSecond = Optional.empty();
            private DateComparison dateComparison;

            public enum DateComparison {
                AT,
                AFTER,
                BEFORE,
                BETWEEN

            }
        }
    }

    @Override
    public Specification<Employee> toSpecification() {

        final Specification<Employee>[] specification = new Specification[]{Specification.where(null)};

        filter.getKeyword().ifPresent(keyword ->
                specification[0] = specification[0].and(EmployeeSpecification.search(keyword)));

        filter.getFirstName().ifPresent(firstName ->
                specification[0] = specification[0].and(EmployeeSpecification.hasFirstName(firstName)));

        filter.getLastName().ifPresent(lastName ->
                specification[0] = specification[0].and(EmployeeSpecification.hasLastName(lastName)));

        filter.getDepartmentName().ifPresent(departmentName ->
                specification[0] = specification[0].and(EmployeeSpecification.hasCategoryNameLike(departmentName)));

        filter.getBirthdayDate().ifPresent(birthdayDate -> {
            Date date = birthdayDate.getBirthdayDateFirst().orElse(null);
            Filter.BirthdayDate.DateComparison dateComparison = birthdayDate.getDateComparison();

            if (date != null && dateComparison != null) {
                specification[0] = switch (dateComparison) {
                    case AT -> specification[0].and(EmployeeSpecification.birthdayDatedAt(date));
                    case AFTER -> specification[0].and(EmployeeSpecification.birthdayDateAfterThan(date));
                    case BEFORE -> specification[0].and(EmployeeSpecification.birthdayDateBeforeThan(date));
                    case BETWEEN -> {
                        Date startDate = birthdayDate.getBirthdayDateFirst().orElse(null);
                        Date endDate = birthdayDate.getBirthdayDateSecond().orElse(null);
                        yield specification[0].and(EmployeeSpecification.birthdayDateBetween(startDate, endDate));
                    }
                    default -> specification[0];
                };
            }
        });

        return specification[0];
    }
}
