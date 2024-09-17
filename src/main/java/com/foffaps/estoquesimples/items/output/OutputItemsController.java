package com.foffaps.estoquesimples.items.output;

import com.foffaps.estoquesimples.items.Items;
import com.foffaps.estoquesimples.utils.exceptions.BadRequestException;
import com.foffaps.estoquesimples.utils.exceptions.NotFoundException;
import com.foffaps.estoquesimples.utils.models.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("outputItemss")
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
}