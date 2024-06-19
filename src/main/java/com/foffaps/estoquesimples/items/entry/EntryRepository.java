package com.foffaps.estoquesimples.items.entry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    List<Entry> findAllByItemId(Long itemId);

//    boolean existsByLotNumber(String lotNumber);

    Page<Entry> findAll(Specification<Entry> specification, Pageable pageable);
}