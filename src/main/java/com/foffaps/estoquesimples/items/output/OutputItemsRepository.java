package com.foffaps.estoquesimples.items.output;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OutputItemsRepository extends JpaRepository<OutputItems, Long> {
    List<OutputItems> findAllByItemId(Long itemId);

    boolean existsByLotNumber(String lotNumber);

    Page<OutputItems> findAll(Specification<OutputItems> specification, Pageable pageable);
}