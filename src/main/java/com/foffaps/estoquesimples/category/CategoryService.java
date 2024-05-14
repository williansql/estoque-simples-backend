package com.foffaps.estoquesimples.category;

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
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryCriteria criteria;


    public Category create(Category category) throws BadRequestException {
        Optional<Category> existsCategory = repository.findByNameIgnoreCase(category.getName());
        if (existsCategory.isPresent())
            throw  new BadRequestException("Já existe uma categoria com esse nome.");
        return repository.save(category);
    }


    public PaginatedData<Category> findAllCategory(CategoryCriteria categoryCriteria, Pageable pageable) {
        PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.ASC, "name")
        );
        Specification<Category> specification = categoryCriteria.createSpecification(criteria);
        Page<Category> category = repository.findAll(specification, pageable);

        return new PaginatedData<>(category.getContent(), Pagination.from(category, pageable));

    }

    public Category findById(String id) throws NotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Categoria não encontrada."));
    }


    public Category update(String id, Category category) throws NotFoundException, BadRequestException {
        Optional<Category> existingCategory =
                repository.findByNameIgnoreCase(category.getName());
        if (existingCategory.isEmpty())
            throw new BadRequestException("Já existe uma categoria com esse nome");
        Category findCategory = repository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Categoria não encontrada"));
        repository.save(findCategory);
        return findCategory;
    }

    public void deleteById(String id) throws NotFoundException {
        repository.findById(id).orElseThrow(
                () -> new NotFoundException("Categoria não encontrada"));
        repository.deleteById(id);
    }
}
