package com.foffaps.estoquesimples.items;

import com.foffaps.estoquesimples.utils.exceptions.BadRequestException;
import com.foffaps.estoquesimples.utils.exceptions.NotFoundException;
import com.foffaps.estoquesimples.utils.models.ApiResponse;
import com.foffaps.estoquesimples.utils.models.PaginatedData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("items")
public class ItemsController {

    private final ItemsService service;

    @PostMapping
    public ResponseEntity<ApiResponse<Items>> create(@RequestBody @Valid ItemsDTO itemsDTO)
            throws BadRequestException {
        ApiResponse<Items> response = new ApiResponse<>();
        var items = new Items();
        BeanUtils.copyProperties(itemsDTO, items);
        Items savedItems = service.create(items);
        response.of(HttpStatus.CREATED, "Item " + savedItems.getName().toUpperCase() + " criado.", savedItems);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginatedData<Items>>> findAll(
            ItemsCriteria criteria, @PageableDefault(size = 5) Pageable pageable) {
        ApiResponse<PaginatedData<Items>> response = new ApiResponse<>();
        PaginatedData<Items> paginatedData = service.findAll(criteria, pageable);
        response.of(HttpStatus.OK, "Lista de items", paginatedData);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Items>> findById(@PathVariable Long id)
    throws NotFoundException {
        ApiResponse<Items> response = new ApiResponse<>();
        Items items = service.findById(id);
        response.of(HttpStatus.OK, "Item " + items.getName().toUpperCase() + " encontrado.", items);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Items>> update(
            @PathVariable Long id, @Valid @RequestBody ItemsDTO itemsDTO)
                throws BadRequestException, NotFoundException{
        ApiResponse<Items> response = new ApiResponse<>();
        var items = new Items();
        BeanUtils.copyProperties(itemsDTO, items);
        Items savedItems = service.update(id, items);
        response.of(HttpStatus.OK, "Item " + savedItems.getName().toUpperCase() + " atualizado.", savedItems);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Items>> delete(@PathVariable Long id)
            throws NotFoundException{
        ApiResponse<Items> response = new ApiResponse<>();
        service.delete(id);
        response.of(HttpStatus.OK, "Item deletado.");
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}



