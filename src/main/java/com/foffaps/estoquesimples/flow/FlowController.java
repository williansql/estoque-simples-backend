package com.foffaps.estoquesimples.flow;

import com.foffaps.estoquesimples.utils.exceptions.BadRequestException;
import com.foffaps.estoquesimples.utils.models.ApiResponse;
import com.foffaps.estoquesimples.utils.models.PaginatedData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flow")
@RequiredArgsConstructor
public class FlowController {

    private final FlowService flowService;
    private final FlowCriteria flowCriteria;

    @GetMapping
    public ResponseEntity<ApiResponse<PaginatedData<Flow>>> findAll(
            FlowCriteria criteria, @PageableDefault(size = 10) Pageable pageable){
        ApiResponse<PaginatedData<Flow>> response = new ApiResponse<>();
        PaginatedData<Flow> data = flowService.findAll(criteria, pageable);
        response.of(
                HttpStatus.OK,
                "Lista encontrada.",
                data);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Flow>> findById(@PathVariable String id) throws BadRequestException {
        ApiResponse<Flow> response = new ApiResponse<>();
        Flow flow = flowService.findById(id);
        response.of(
                HttpStatus.OK,
                "Fluxo de estoque encontrado.",
                flow);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
