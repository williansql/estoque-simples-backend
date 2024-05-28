package com.foffaps.estoquesimples.subcategory;

import com.foffaps.estoquesimples.utils.exceptions.BadRequestException;
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
@RequestMapping("subcategory")
@RequiredArgsConstructor
@CrossOrigin(origins = "**")
public class SubcategoryController {

    private final SubcategoryService service;

    @PostMapping
    public ResponseEntity<ApiResponse<Subcategory>> create(
            @RequestBody SubcategoryDTO subcategryDTO) throws BadRequestException {
        ApiResponse<Subcategory> response = new ApiResponse<>();
        var subcategory = new Subcategory();
        BeanUtils.copyProperties(subcategryDTO, subcategory);
        Subcategory saveSubcategory = service.create(subcategory);
        response.of(
                HttpStatus.CREATED,
                "Subcategoria  "
                        + saveSubcategory.getName().toUpperCase() +
                        " criada com sucesso.",
                saveSubcategory
        );
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginatedData<Subcategory>>> findAll(
            SubcategoryCriteria criteria, @PageableDefault(size = 5) Pageable pageable) {
        ApiResponse<PaginatedData<Subcategory>> response = new ApiResponse<>();
        PaginatedData<Subcategory> pageSubcategory = service.findAll(criteria, pageable);
        response.of(
                HttpStatus.OK,
                "Subcategorias encontradas ",
                pageSubcategory
        );
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Subcategory>> findById(
            @PathVariable String id) throws NotFoundException {
        ApiResponse<Subcategory> response = new ApiResponse<>();
        Subcategory foundSubcategory = service.findById(id);
        response.of(
                HttpStatus.FOUND,
                "Subcategoria "
                        + foundSubcategory.getName().toUpperCase() +
                        " encontrada com sucesso."
        );
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Subcategory>> update(
            @PathVariable String id, @RequestPart SubcategoryDTO subcategoryDTO)
            throws NotFoundException, BadRequestException {
        ApiResponse<Subcategory> response = new ApiResponse<>();
        var subcategory = new Subcategory();
        BeanUtils.copyProperties(subcategoryDTO, subcategory);
        Subcategory updateSubcategory = service.update(id, subcategory);
        response.of(
                HttpStatus.OK,
                "Subcategoria "
                        + updateSubcategory.getName().toUpperCase() +
                        " editada com sucesso.",
                updateSubcategory
        );
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Subcategory>> deleteById(
            @PathVariable String id) throws NotFoundException {
        ApiResponse<Subcategory> response = new ApiResponse<>();
        service.deleteById(id);
        response.of(
                HttpStatus.OK,
                "Subcategoria deletada com sucesso."
        );
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
