package com.springboot.jpaspecification.dto;

import com.springboot.jpaspecification.entity.Employee;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

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
        private String name;
        private Set<Long> departmentIds;
        private BirthdayDate birthdayDate;

        @Getter
        @Setter
        private static class BirthdayDate {

            private Date createdAt;
            private DateComparison dateComparison;

            private enum DateComparison {
                AT,
                AFTER,
                BEFORE
            }
        }

    }


    @Override
    public Specification<Employee> toSpecification() {

        Specification<Employee> specification = Specification.where(null);

        return specification;
    }
}
