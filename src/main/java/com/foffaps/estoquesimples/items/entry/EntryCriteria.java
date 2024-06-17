package com.foffaps.estoquesimples.items.entry;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Data
public class EntryCriteria {

    String lotNumber;
    String responsibleName;
    String responsibleCpf;
    String validationDate;
    String sku;

    public Specification<Entry> createSpecification(EntryCriteria criteria) {
        Specification<Entry> specification = Specification.where(null);
        if (criteria.getLotNumber() != null) {
            specification = specification.and(EntryCriteria.searchLotNumber(criteria.getLotNumber()));
        }
        if (criteria.getResponsibleName() != null) {
            specification = specification.and(EntryCriteria.searchResponsibleName(criteria.getResponsibleName()));
        }
        if (criteria.getResponsibleCpf() != null) {
            specification = specification.and(EntryCriteria.searchResponsibleCpf(criteria.getResponsibleCpf()));
        }
        if (criteria.getValidationDate() != null) {
            specification = specification.and(EntryCriteria.searchValidationDate(criteria.getValidationDate()));
        }
        if (criteria.getSku() != null) {
            specification = specification.and(EntryCriteria.searchSku(criteria.getSku()));
        }
        return specification;
    }

    private static Specification<Entry> searchLotNumber(String lotNumber) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(
                        root.get("lotNumber")), "%" + lotNumber.toLowerCase() + "%"));
    }

    private static Specification<Entry> searchResponsibleName(String responsibleName) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(
                        root.get("responsibleName")), "%" + responsibleName.toLowerCase() + "%"));
    }

    private static Specification<Entry> searchResponsibleCpf(String responsibleCpf) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(
                        root.get("responsibleCpf")), "%" + responsibleCpf.toLowerCase() + "%"));
    }

    private static Specification<Entry> searchValidationDate(String validationDate) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(
                        root.get("validationDate")), "%" + validationDate.toLowerCase() + "%"));
    }

    private static Specification<Entry> searchSku(String sku) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(
                        root.get("sku")), "%" + sku.toLowerCase() + "%"));
    }
}
