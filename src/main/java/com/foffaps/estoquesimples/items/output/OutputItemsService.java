package com.foffaps.estoquesimples.items.output;

import com.foffaps.estoquesimples.items.items.ItemsRepository;
import com.foffaps.estoquesimples.person.supplier.SupplierRepository;
import com.foffaps.estoquesimples.utils.exceptions.BadRequestException;
import com.foffaps.estoquesimples.utils.exceptions.NotFoundException;
import com.foffaps.estoquesimples.utils.models.PaginatedData;
import com.foffaps.estoquesimples.utils.models.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OutputItemsService {

    private final OutputItemsRepository outputItemsRepository;
    private final OutputItemsCriteria outputItemsCriteria;
    private final ItemsRepository itemsRepository;
    private final SupplierRepository supplierRepository;
    private final OutputItemsOptional optional;

    @Transactional
    public OutputItems create(OutputItems outputItems) throws NotFoundException, BadRequestException {
        optional.existsByLotNumber(outputItems.getLotNumber());
        return outputItemsRepository.save(outputItems);
    }
    public PaginatedData<OutputItems> findAll(OutputItemsCriteria criteria, Pageable pageable) {
        Specification<OutputItems> specification = outputItemsCriteria.createSpecification(criteria);
        Page<OutputItems> data = outputItemsRepository.findAll(specification, pageable);
        return new PaginatedData<>(data.getContent(), Pagination.from(data, pageable));
    }
}