package com.springboot.jpaspecification.dto;

import org.springframework.data.jpa.domain.Specification;

public interface Filterable<T> {

    Specification<T> toSpecification();
}
