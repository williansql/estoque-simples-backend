package com.foffaps.estoquesimples.flow;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowRepository extends JpaRepository<Flow, String> {

    Page<Flow> findAll(Specification<Flow> specification, Pageable pageable);
}
