package com.foffaps.estoquesimples.items.exit;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Data
public class ExitCriteria {

    String lotNumber;
    String responsibleName;
    String responsibleCpf;

    public Specification<Exit> createSpecification(ExitCriteria criteria) {
        Specification<Exit> specification = Specification.where(null);
        if (criteria.getLotNumber() != null) {
            specification = specification.and(ExitCriteria.searchLotNumber(criteria.getLotNumber()));
        }
        if (criteria.getResponsibleName() != null) {
            specification = specification.and(ExitCriteria.searchResponsibleName(criteria.getResponsibleName()));
        }
        if (criteria.getResponsibleCpf() != null) {
            specification = specification.and(ExitCriteria.searchResponsibleCpf(criteria.getResponsibleCpf()));
        }
        return specification;
    }

    private static Specification<Exit> searchLotNumber(String lotNumber) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(
                        root.get("lotNumber")), "%" + lotNumber.toLowerCase() + "%"));
    }

    private static Specification<Exit> searchResponsibleName(String responsibleName) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(
                        root.get("responsibleName")), "%" + responsibleName.toLowerCase() + "%"));
    }

    private static Specification<Exit> searchResponsibleCpf(String responsibleCpf) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(
                        root.get("responsibleCpf")), "%" + responsibleCpf.toLowerCase() + "%"));
    }
}
