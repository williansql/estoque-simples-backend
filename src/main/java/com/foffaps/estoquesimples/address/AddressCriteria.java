package com.foffaps.estoquesimples.address;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Data
@Service
public class AddressCriteria {

    private String publicPlace;
    private String district;
    private String city;

    public Specification<Address> createSpecification(AddressCriteria criteria){
        Specification<Address> specs = Specification.where(null);
        if (criteria.getPublicPlace() != null)
            specs.or(AddressCriteria.searchPublicPlace(criteria.publicPlace));
        if (criteria.getDistrict() != null)
            specs.or(AddressCriteria.searchDistrict(criteria.getDistrict()));
        if (criteria.getCity() != null)
            specs.or(AddressCriteria.searchCity(criteria.getCity()));
        return specs;
    }

    private static Specification<Address> searchPublicPlace(String publicPlace){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(
                        root.get("publicPlace")), "%" + publicPlace.toLowerCase() + "%"));
    }

    private static Specification<Address> searchDistrict(String district){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(
                        root.get("district")), "%" + district.toLowerCase() + "%"));
    }

    private static Specification<Address> searchCity(String city){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(
                        root.get("city")), "%" + city.toLowerCase() + "%"));
    }
}
