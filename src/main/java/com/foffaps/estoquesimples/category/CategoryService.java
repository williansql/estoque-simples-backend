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
    private final CategoryCriteria categoryCriteria;


    public Category create(Category category) throws BadRequestException {
        if (category.getName() == null || category.getName().isEmpty())
            throw new BadRequestException("O campo 'NOME' não pode ser vazio.");
        Optional<Category> existsCategory = repository.findByNameIgnoreCase(category.getName().toLowerCase());
        if (existsCategory.isPresent())
            throw new BadRequestException(
                    "Já existe uma categoria com o nome: "
                            + existsCategory.get().getName().toUpperCase());
        return repository.save(category);
    }


    public PaginatedData<Category> findAllCategory(CategoryCriteria criteria, Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.ASC, "name")
        );
        Specification<Category> specification = categoryCriteria.createSpecification(criteria);
        Page<Category> category = repository.findAll(specification, pageable);

        return new PaginatedData<>(category.getContent(), Pagination.from(category, pageable));

    }

    public Category findById(Integer id) throws NotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Categoria não encontrada."));
    }


    public Category update(Integer id, Category category) throws NotFoundException, BadRequestException {
        Category existingCategory = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi encontrada uma categoria com o ID: " + id));

        if (category.getName() == null || category.getName().isBlank()) {
            throw new BadRequestException("O nome da categoria não pode ser nulo ou vazio");
        }

        existingCategory.setName(category.getName());
        return repository.save(existingCategory);
    }

    public void deleteById(Integer id) throws NotFoundException {
        repository.findById(id).orElseThrow(
                () -> new NotFoundException("Categoria não encontrada"));
        repository.deleteById(id);
    }
}