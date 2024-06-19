package com.foffaps.estoquesimples.flow;

import com.foffaps.estoquesimples.items.Items;
import com.foffaps.estoquesimples.items.entry.Entry;
import com.foffaps.estoquesimples.items.exit.Exit;
import com.foffaps.estoquesimples.person.supplier.Supplier;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class FlowCriteria {

    private List<Long> itemsId;
    private List<Long> supplierId;
    private List<Long> entryId;
    private List<Long> exitId;

    public Specification<Flow> createSpecification(FlowCriteria criteria){
        Specification<Flow> specs = Specification.where(null);
        if(criteria.getItemsId() != null){
            specs = specs.or(FlowCriteria.findByItemsId(criteria.getItemsId()));
        }
        if(criteria.getSupplierId() != null){
            specs = specs.or(FlowCriteria.findBySupplierId(criteria.getSupplierId()));
        }
        if(criteria.getEntryId() != null){
            specs = specs.or(FlowCriteria.findByEntryId(criteria.getEntryId()));
        }
        if(criteria.getExitId() != null){
            specs = specs.or(FlowCriteria.findByExitId(criteria.getExitId()));
        }
        return specs;
    }

    private static Specification<Flow> findByItemsId(List<Long> itemsId) {
        return (root, query, criteriaBuilder) -> root.get("item").get("id").in(itemsId);
    }

    private static Specification<Flow> findBySupplierId(List<Long> supplierId) {
        return (root, query, criteriaBuilder) -> root.get("supplier").get("id").in(supplierId);
    }

    private static Specification<Flow> findByEntryId(List<Long> entryId) {
        return (root, query, criteriaBuilder) -> root.get("entry").get("id").in(entryId);
    }

    private static Specification<Flow> findByExitId(List<Long> exitId) {
        return (root, query, criteriaBuilder) -> root.get("exit").get("id").in(exitId);
    }
}
