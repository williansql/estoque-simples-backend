package com.foffaps.estoquesimples.subcategory;

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
public class SubcategoryService {

    private final SubcategoryRepository repository;
    private final SubcategoryCriteria subcategoryCriteria;

    public Subcategory create(Subcategory subcategory) throws BadRequestException {
        Optional<Subcategory> existingSubcategory = repository.findByNameIgnoreCase(subcategory.getName());
        Integer existingCategory = subcategory.getCategory().getId();
        if (existingCategory == null)
            throw new BadRequestException("Selecione uma categoria.");
        if (existingSubcategory.isPresent())
            throw new BadRequestException("Já existe uma categoria com esse nome");
        return repository.save(subcategory);
    }

    public PaginatedData<Subcategory> findAll(SubcategoryCriteria criteria, Pageable pageable) {
        PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.ASC, "name")
        );
        Specification<Subcategory> specification = subcategoryCriteria.createSpecification(criteria);
        Page<Subcategory> subcategory = repository.findAll(specification, pageable);

        return new  PaginatedData<>(subcategory.getContent(), Pagination.from(subcategory, pageable));
    }

    public Subcategory findById(Integer id) throws NotFoundException {
        Subcategory foundSubcategory = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Subcategoria não encontrada"));
        return foundSubcategory;
    }

    public Subcategory update(Integer id, Subcategory subcategory) throws NotFoundException, BadRequestException {
        Optional<Subcategory> existingSubcategoria = repository.findByNameIgnoreCase(subcategory.getName());
        if (existingSubcategoria.isPresent())
            throw new BadRequestException("Já existe uma categoria com esse nome.");
        Subcategory editSubcategory = repository.findById(id).orElse(null);
        if (editSubcategory == null)
            throw new NotFoundException("Subcategoria não encontrada");
        editSubcategory.setName(subcategory.getName());
        editSubcategory.setCategory(subcategory.getCategory());
        repository.save(editSubcategory);
        return editSubcategory;
    }

    public void deleteById(Integer id) throws NotFoundException {
        Subcategory deleteSubcategory = repository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Categoria não encontrada"));
        repository.deleteById(deleteSubcategory.getId());
    }
}
