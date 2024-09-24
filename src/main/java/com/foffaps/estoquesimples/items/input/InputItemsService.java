package com.foffaps.estoquesimples.items.input;

import com.foffaps.estoquesimples.items.items.Items;
import com.foffaps.estoquesimples.items.items.ItemsRepository;
import com.foffaps.estoquesimples.utils.exceptions.BadRequestException;
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
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InputItemsService {

    private final InputItemsRepository inputItemsRepository;
    private final ItemsRepository itemsRepository;
    private final InputItemsCriteria inputItemsCriteria;

    @Transactional
    public InputItems createEntry(InputItems inputItems) throws NotFoundException {

        boolean existsLotNumber = inputItemsRepository.existsByLotNumber(inputItems.getLotNumber());
        if (existsLotNumber)
            throw new BadRequestException("Esse número de lote já existe");

        List<Items> itemsList = new ArrayList<>();
        if (inputItems.getItems() != null) {
            for (Items items : inputItems.getItems()){
                Items existItems = itemsRepository.findById(items.getId())
                        .orElseThrow(() -> new NotFoundException("Item não encontrado."));
                existItems.setQtd(existItems.getQtd() + inputItems.getQuantity());
                itemsList.add(existItems);
            }
            inputItems.setItems(itemsList);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (inputItems.getFabricationDate() != null) {
            LocalDate.parse(inputItems.getFabricationDate().formatted(formatter));
        }
        if (inputItems.getValidationDate() != null) {
            LocalDate.parse(inputItems.getValidationDate().formatted(formatter));
        }
        inputItems.setDateEntry(LocalDateTime.now());
        itemsRepository.saveAll(itemsList);
        return inputItemsRepository.save(inputItems);
    }

    public PaginatedData<InputItems> findAll(InputItemsCriteria criteria, Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.ASC, "validationDate"));
        Specification<InputItems> specification = inputItemsCriteria.createSpecification(criteria);
        Page<InputItems> pageEntry = inputItemsRepository.findAll(specification, pageable);
        return new PaginatedData<>(pageEntry.getContent(), Pagination.from(pageEntry, pageable));
    }
}