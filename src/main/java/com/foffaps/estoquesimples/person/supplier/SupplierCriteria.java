package com.foffaps.estoquesimples.person.supplier;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Data
public class SupplierCriteria {

    private String name;
    private String identity;
    private String fantasyName;

    public Specification<Supplier> createSpecification(SupplierCriteria criteria){
        Specification<Supplier> specs = Specification.where(null);
        if(criteria.getName() != null)
            specs.or(SupplierCriteria.searchName(criteria.getName()));
        if (criteria.getFantasyName() != null)
            specs.or(SupplierCriteria.searchFantasyName(criteria.getFantasyName()));
        if (criteria.getIdentity() != null)
            specs.or(SupplierCriteria.searchIdentity(criteria.getIdentity()));
        return specs;
    }

    private static Specification<Supplier> searchName(String name) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(
                        root.get("name")), "%" + name.toLowerCase() + "%"));
    }

    private static Specification<Supplier> searchIdentity(String identity) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(
                        root.get("name")), "%" + identity.toLowerCase() + "%"));
    }

    private static Specification<Supplier> searchFantasyName(String fantasyName) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(
                        root.get("name")), "%" + fantasyName.toLowerCase() + "%"));
    }

}
