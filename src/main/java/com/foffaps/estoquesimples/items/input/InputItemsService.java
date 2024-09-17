package com.foffaps.estoquesimples.items.input;

import com.foffaps.estoquesimples.flow.Flow;
import com.foffaps.estoquesimples.flow.FlowRepository;
import com.foffaps.estoquesimples.items.Items;
import com.foffaps.estoquesimples.items.ItemsRepository;
import com.foffaps.estoquesimples.items.lot.Lot;
import com.foffaps.estoquesimples.items.lot.LotRepository;
import com.foffaps.estoquesimples.utils.exceptions.NotFoundException;
import com.foffaps.estoquesimples.utils.models.PaginatedData;
import com.foffaps.estoquesimples.utils.models.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class InputItemsService {

    private final InputItemsRepository inputItemsRepository;
    private final ItemsRepository itemsRepository;
    private final InputItemsCriteria inputItemsCriteria;
    private final LotRepository lotRepository;

    @Transactional
    public InputItems createEntry(Long itemId, InputItems inputItems) throws NotFoundException {
        Items item = itemsRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Item não encontrado"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (inputItems.getFabricationDate() != null) {
            LocalDate.parse(inputItems.getFabricationDate().formatted(formatter));
        }
        if (inputItems.getValidationDate() != null) {
            LocalDate.parse(inputItems.getValidationDate().formatted(formatter));
        }
        inputItems.setDateEntry(LocalDateTime.now());
        inputItems.setItem(item);
        itemsRepository.findById(itemId).map(
                it -> {
                    it.setQtd(it.getQtd() + inputItems.getQuantity());
                    it.setBuyPrice(inputItems.getBuyPrice());
                    return it;
                }
        );
        return inputItemsRepository.save(inputItems);
    }

    public PaginatedData<InputItems> findAll(InputItemsCriteria criteria, Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.ASC, "lotNumber"));
        Specification<InputItems> specification = inputItemsCriteria.createSpecification(criteria);
        Page<InputItems> pageEntry = inputItemsRepository.findAll(specification, pageable);
        return new PaginatedData<>(pageEntry.getContent(), Pagination.from(pageEntry, pageable));
    }
}