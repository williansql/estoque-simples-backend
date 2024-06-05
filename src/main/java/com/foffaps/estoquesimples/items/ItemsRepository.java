package com.foffaps.estoquesimples.items;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Long> {
    Optional<Items> findByNameIgnoreCase(String name);

    Page<Items> findAll(Specification<Items> specification, Pageable pageable);

    boolean existsByCodItem(String codItem);
}
