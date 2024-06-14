package com.foffaps.estoquesimples.items.entry;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    List<Entry> findAllByItemId(Long itemId);

    boolean existsByLotNumber(String lotNumber);
}