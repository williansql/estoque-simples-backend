package com.foffaps.estoquesimples.items;

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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemsService {

    private final ItemsRepository repository;
    private final ItemsCriteria itemsCriteria;

    public Items create(Items items) throws BadRequestException {
        Optional<Items> existingItems = repository.findByNameIgnoreCase(items.getName());
        if (existingItems.isPresent())
            throw new BadRequestException("Item " + items.getName().toUpperCase() + " já existe.");
        if (items.getCategory() == null) {
            throw new BadRequestException("A categoria não pode ser nula.");
        }
        String codItem = generateItemCode(items.getName());
        items.setCodItem(codItem);
        return repository.save(items);
    }

    public PaginatedData<Items> findAll(ItemsCriteria criteria, Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.ASC, "name")
        );

        Specification<Items> specification = itemsCriteria.createSpecification(criteria);
        Page<Items> itemsPage = repository.findAll(specification, pageable);
        return new PaginatedData<>(itemsPage.getContent(), Pagination.from(itemsPage, pageable));
    }

    public Items findById(Long id) throws NotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Categoria não encontrada."));
    }

    public Items update(Long id, Items items) throws NotFoundException, BadRequestException {
        if(repository.findByNameIgnoreCase(items.getName()).isPresent())
            throw new BadRequestException("Já existe um item com esse nome");
        Items existingItems = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Não foi encontrada uma categoria com o ID: " + id.getClass().getName().toUpperCase()));
        if (items.getName() == null || items.getName().isBlank()) {
            throw new BadRequestException("O nome da categoria não pode ser nulo ou vazio");
        }
        existingItems.setName(items.getName());
        existingItems.setModel(items.getModel());
        existingItems.setBranding(items.getBranding());
        existingItems.setDescription(items.getDescription());
        existingItems.setBuyPrice(items.getBuyPrice());
        existingItems.setSellPrice(items.getSellPrice());
        existingItems.setSku(items.getSku());
        existingItems.setManufacturerDate(items.getManufacturerDate());
        existingItems.setSellDate(items.getSellDate());
        existingItems.setStatusEnum(items.getStatusEnum());
        existingItems.setUnitMeasureQtd(items.getUnitMeasureQtd());
        existingItems.setValidationDate(items.getValidationDate());
        existingItems.setCategory(items.getCategory());
        if (existingItems.getCodItem() == null) {
            String codItem = generateItemCode(existingItems.getName());
            existingItems.setCodItem(codItem);
        }
        return repository.save(existingItems);
    }


    public void delete(Long id) throws NotFoundException {
        repository.findById(id).orElseThrow(
                () -> new NotFoundException("Item não encontrado"));
        repository.deleteById(id);
    }

    private String generateItemCode(String itemName) {
        String firstLetter = itemName.substring(0, 1).toLowerCase();
        int counter = 1;
        String codItem;
        do {
            codItem = String.format("%s%03d", firstLetter, counter++);
        } while (repository.existsByCodItem(codItem));
        return codItem;
    }
}
