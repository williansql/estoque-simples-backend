package com.foffaps.estoquesimples.category;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Data
@NoArgsConstructor
public class CategoryCriteria {

    private String name;

    public Specification<Category> createSpecification(CategoryCriteria criteria) {
        Specification<Category> specs = Specification.where(null);
        if (criteria.getName() != null) {
            specs = specs.or(CategoryCriteria.searchName(criteria.getClass().getName()));
        }
        return specs;
    }

    private static Specification<Category> searchName(String name) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));

    }


}
