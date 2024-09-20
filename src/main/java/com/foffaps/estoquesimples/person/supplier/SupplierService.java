package com.foffaps.estoquesimples.person.supplier;

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
public class SupplierService {

    private final SupplierRepository repository;
    private final SupplierCriteria supplierCriteria;

    public Supplier create(Supplier supplier) throws BadRequestException {
        Optional<Supplier> existsSupplier = repository.findByNameIgnoreCase(supplier.getName());
        Optional<Supplier> existsFantasy = repository.findByFantasyNameIgnoreCase(supplier.getFantasyName());
        if (existsSupplier.isPresent() || existsFantasy.isPresent())
            throw new BadRequestException("Esta empresa já esta cadastrada.");
        return repository.save(supplier);
    }

    public PaginatedData<Supplier> findAll(SupplierCriteria criteria, Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.ASC, "name")
        );
        Specification<Supplier> specification = supplierCriteria.createSpecification(criteria);
        Page<Supplier> supplierPage = repository.findAll(specification, pageable);
        return new PaginatedData<>(supplierPage.getContent(), Pagination.from(supplierPage, pageable));
    }

    public Supplier update(Long id, Supplier supplier) throws BadRequestException, NotFoundException {
        Optional<Supplier> existsSupplier = repository.findByNameIgnoreCase(supplier.getName());
        Optional<Supplier> existsFantasy = repository.findByNameIgnoreCase(supplier.getFantasyName());
        if (existsSupplier.isPresent() || existsFantasy.isPresent())
            throw new BadRequestException("Este fornecedor já foi cadastrado.");
        return repository.findById(id).map(repository::save).orElseThrow(() ->
            new NotFoundException("Fornecedor não encontrado.")
        );
    }

    public void deleteById(Long id) throws NotFoundException {
        Supplier existId = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Forncedor não encontrado."));
        repository.deleteById(existId.getId());
    }
}