package com.foffaps.estoquesimples.items.exit;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExitRepository extends JpaRepository<Exit, Long> {
    List<Exit> findAllByItemId(Long itemId);

    boolean existsByLotNumber(String lotNumber);
}
