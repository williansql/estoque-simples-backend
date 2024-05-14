package com.foffaps.estoquesimples.subcategory;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Data
public class SubcategoryCriteria {

    private String name;

    public Specification<Subcategory> createSpecification(SubcategoryCriteria criteria) {
        Specification<Subcategory> specs = Specification.where(null);
        if (criteria.getName() != null) {
            specs = specs.or(SubcategoryCriteria.searchName(criteria.getName()));
        }
        return specs;
    }

    private static Specification<Subcategory> searchName(String name) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));

    }


}
