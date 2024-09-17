package com.foffaps.estoquesimples.items.output;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Data
public class OutputItemsCriteria {

    String lotNumber;
    String responsibleName;
    String responsibleCpf;

    public Specification<OutputItems> createSpecification(OutputItemsCriteria criteria) {
        Specification<OutputItems> specification = Specification.where(null);
        if (criteria.getLotNumber() != null) {
            specification = specification.and(OutputItemsCriteria.searchLotNumber(criteria.getLotNumber()));
        }
        if (criteria.getResponsibleName() != null) {
            specification = specification.and(OutputItemsCriteria.searchResponsibleName(criteria.getResponsibleName()));
        }
        if (criteria.getResponsibleCpf() != null) {
            specification = specification.and(OutputItemsCriteria.searchResponsibleCpf(criteria.getResponsibleCpf()));
        }
        return specification;
    }

    private static Specification<OutputItems> searchLotNumber(String lotNumber) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(
                        root.get("lotNumber")), "%" + lotNumber.toLowerCase() + "%"));
    }

    private static Specification<OutputItems> searchResponsibleName(String responsibleName) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(
                        root.get("responsibleName")), "%" + responsibleName.toLowerCase() + "%"));
    }

    private static Specification<OutputItems> searchResponsibleCpf(String responsibleCpf) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(
                        root.get("responsibleCpf")), "%" + responsibleCpf.toLowerCase() + "%"));
    }
}