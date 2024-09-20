package com.foffaps.estoquesimples.items.output;

import com.foffaps.estoquesimples.utils.models.ApiResponse;
import com.foffaps.estoquesimples.utils.models.PaginatedData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("output")
@RequiredArgsConstructor
public class OutputItemsController {

    private final OutputItemsService outputItemsService;

    @PostMapping("/{itemId}")
    public ResponseEntity<ApiResponse<OutputItems>> createEntry(
            @PathVariable Long itemID,
            @RequestBody OutpuItemsDTO outpuItemsDTO) {
        ApiResponse<OutputItems> response = new ApiResponse<>();
        var outputItems = new OutputItems();
        BeanUtils.copyProperties(outpuItemsDTO, outputItems);
        OutputItems save = outputItemsService.create(itemID, outputItems);
        response.of(
                HttpStatus.CREATED,
                "Entrada registrada com sucesso, Lote Nº: " + save.getLotNumber(),
                save);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginatedData<OutputItems>>> findAll(
            OutputItemsCriteria criteria,
            @PageableDefault(size = 10, sort = "dateOutput", direction = Sort.Direction.ASC)Pageable pageable) {
                ApiResponse<PaginatedData<OutputItems>> response = new ApiResponse<>();
                PaginatedData<OutputItems> data = outputItemsService.findAll(criteria, pageable);
                response.of(
                        HttpStatus.OK,
                        "Lista de saídas abaixo",
                        data);
                return ResponseEntity.status(response.getStatus()).body(response);
    }
}