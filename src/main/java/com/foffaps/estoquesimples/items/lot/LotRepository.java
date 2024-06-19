package com.foffaps.estoquesimples.items.lot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long> {
}
