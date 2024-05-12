package com.foffaps.estoquesimples.category;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CategoryCriteria {

    private String name;

    public Specification<CategoryDTO> createSpecification(CategoryCriteria criteria) {
        Specification<CategoryDTO> specs = Specification.where(null);
        if (criteria.getClass().getName() != null) {
            specs = specs.or(CategoryCriteria.searchName(criteria.getClass().getName()));
        }
        return specs;
    }

    private static Specification<CategoryDTO> searchName(String name) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));

    }


}
