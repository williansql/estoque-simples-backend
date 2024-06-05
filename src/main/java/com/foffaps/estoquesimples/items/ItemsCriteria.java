package com.foffaps.estoquesimples.items;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Data
public class ItemsCriteria {

    private String name;

    public Specification<Items> createSpecification(ItemsCriteria criteria) {
        Specification<Items> specs = Specification.where(null);
        if (criteria.getName() != null) {
            specs = specs.or(ItemsCriteria.searchName(criteria.getName()));
        }
        return specs;
    }

    private static Specification<Items> searchName(String name) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(
                        root.get("name")), "%" + name.toLowerCase() + "%"));
    }
}
