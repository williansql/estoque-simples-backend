package com.foffaps.estoquesimples.items.input;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InputItemsRepository extends JpaRepository<InputItems, Long> {

    boolean existsByLotNumber(String lotNumber);

    Page<InputItems> findAll(Specification<InputItems> specification, Pageable pageable);

}