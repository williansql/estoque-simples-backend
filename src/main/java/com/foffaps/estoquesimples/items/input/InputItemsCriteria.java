package com.foffaps.estoquesimples.items.input;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Data
public class InputItemsCriteria {

    String lotNumber;
    String responsibleName;
    String responsibleCpf;
    String validationDate;
    String sku;

    public Specification<InputItems> createSpecification(InputItemsCriteria criteria) {
        Specification<InputItems> specification = Specification.where(null);
        if (criteria.getLotNumber() != null) {
            specification = specification.and(InputItemsCriteria.searchLotNumber(criteria.getLotNumber()));
        }
        if (criteria.getResponsibleName() != null) {
            specification = specification.and(InputItemsCriteria.searchResponsibleName(criteria.getResponsibleName()));
        }
        if (criteria.getResponsibleCpf() != null) {
            specification = specification.and(InputItemsCriteria.searchResponsibleCpf(criteria.getResponsibleCpf()));
        }
        if (criteria.getValidationDate() != null) {
            specification = specification.and(InputItemsCriteria.searchValidationDate(criteria.getValidationDate()));
        }
        if (criteria.getSku() != null) {
            specification = specification.and(InputItemsCriteria.searchSku(criteria.getSku()));
        }
        return specification;
    }

    private static Specification<InputItems> searchLotNumber(String lotNumber) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(
                        root.get("lotNumber")), "%" + lotNumber.toLowerCase() + "%"));
    }

    private static Specification<InputItems> searchResponsibleName(String responsibleName) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(
                        root.get("responsibleName")), "%" + responsibleName.toLowerCase() + "%"));
    }

    private static Specification<InputItems> searchResponsibleCpf(String responsibleCpf) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(
                        root.get("responsibleCpf")), "%" + responsibleCpf.toLowerCase() + "%"));
    }

    private static Specification<InputItems> searchValidationDate(String validationDate) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(
                        root.get("validationDate")), "%" + validationDate.toLowerCase() + "%"));
    }

    private static Specification<InputItems> searchSku(String sku) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(
                        root.get("sku")), "%" + sku.toLowerCase() + "%"));
    }
}