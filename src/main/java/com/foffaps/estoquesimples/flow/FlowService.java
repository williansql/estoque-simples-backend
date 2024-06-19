package com.foffaps.estoquesimples.flow;

import com.foffaps.estoquesimples.utils.exceptions.BadRequestException;
import com.foffaps.estoquesimples.utils.models.PaginatedData;
import com.foffaps.estoquesimples.utils.models.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlowService {

    private final FlowRepository flowRepository;
    private final FlowCriteria flowCriteria;

    public Flow create(Flow flow) throws BadRequestException {
        Flow save = flowRepository.save(flow);
        if (save.equals(null)){
            throw new BadRequestException("Erro ao salvar os dados no fluxo do sistema.");
        }
        return flowRepository.save(flow);
    }

    public PaginatedData<Flow> findAll(FlowCriteria criteria, Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.ASC, "id"));
        Specification<Flow> specification = flowCriteria.createSpecification(criteria);
        Page<Flow> data = flowRepository.findAll(specification, pageable);
        return new PaginatedData<>(data.getContent(), Pagination.from(data, pageable));
    }

    public Flow findById(String id) throws BadRequestException {
        return flowRepository.findById(id).orElseThrow(() -> new BadRequestException("Fluxo de dados do sistema não encontrado."));
    }
}
