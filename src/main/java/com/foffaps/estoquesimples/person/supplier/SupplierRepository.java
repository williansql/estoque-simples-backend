package com.foffaps.estoquesimples.person.supplier;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findByNameIgnoreCase(String name);

    Page<Supplier> findAll(Specification<Supplier> specification, Pageable pageable);

    Optional<Supplier> findByFantasyNameIgnoreCase(String fantasyName);
}
