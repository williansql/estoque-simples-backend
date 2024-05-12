package com.foffaps.estoquesimples.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findByNameIgnoreCase(String name);

    Page<CategoryDTO> findAll(Specification<CategoryDTO> specification, Pageable pageable);
}
