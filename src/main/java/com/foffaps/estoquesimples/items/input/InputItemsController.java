package com.foffaps.estoquesimples.items.input;

import com.foffaps.estoquesimples.utils.exceptions.NotFoundException;
import com.foffaps.estoquesimples.utils.models.ApiResponse;
import com.foffaps.estoquesimples.utils.models.PaginatedData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("input")
@RequiredArgsConstructor
public class InputItemsController {

    private final InputItemsService inputItemsService;

    @PostMapping
    public ResponseEntity<ApiResponse<InputItems>> createEntry(
            @RequestBody InputItemsDTO inputItemsDTO) throws NotFoundException {
        ApiResponse<InputItems> response = new ApiResponse<>();
        var inputItems = new InputItems();
        BeanUtils.copyProperties(inputItemsDTO, inputItems);
        InputItems save = inputItemsService.createEntry(inputItems);
        response.of(
                HttpStatus.CREATED,
                "Entrada criada com sucesso!",
                save);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginatedData<InputItems>>> findAll(
            InputItemsCriteria criteria, @PageableDefault(size = 5) Pageable pageable) {
        ApiResponse<PaginatedData<InputItems>> response = new ApiResponse<>();
        PaginatedData<InputItems> paginatedData = inputItemsService.findAll(criteria, pageable);
        response.of(HttpStatus.OK, "Lista de entradas", paginatedData);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}