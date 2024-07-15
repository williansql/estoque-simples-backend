package com.foffaps.estoquesimples.category;

import com.foffaps.estoquesimples.utils.exceptions.BadRequestException;
import com.foffaps.estoquesimples.utils.exceptions.NotFoundException;
import com.foffaps.estoquesimples.utils.models.ApiResponse;
import com.foffaps.estoquesimples.utils.models.PaginatedData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("category")
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    public ResponseEntity<ApiResponse<Category>> create(
            @RequestBody @Valid CategoryDTO categoryDTO, BindingResult result)
             {
        ApiResponse<Category> response = new ApiResponse<>();
        var category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        Category saveCategory = service.create(category);
        response.of(
                HttpStatus.CREATED,
                "Categoria " + saveCategory.getName().toUpperCase() + " Salva com sucesso.",
                saveCategory
        );
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginatedData<Category>>> findAllCategory(
            CategoryCriteria criteria,
            @PageableDefault(size = 5, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        ApiResponse<PaginatedData<Category>> response = new ApiResponse<>();
        PaginatedData<Category> getCategory = service.findAllCategory(criteria, pageable);
        response.of(
                HttpStatus.OK,
                "Categorias encontrada.",
                getCategory
        );
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> findById(
            @PathVariable Integer id) throws NotFoundException {
        ApiResponse<Category> response = new ApiResponse<>();
        Category foundCategory = service.findById(id);
        response.of(
                HttpStatus.FOUND,
                "Categoria " + foundCategory.getName().toUpperCase() + " encontrada,"
        );
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> updateCategory(
            @PathVariable Integer id, @Valid @RequestBody CategoryDTO categoryDTO) throws NotFoundException, BadRequestException {
        ApiResponse<Category> response = new ApiResponse<>();
        var category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        Category updateCategory = service.update(id, category);
        response.of(
                HttpStatus.OK,
                "Categoria " + updateCategory
                        .getName()
                        .toUpperCase() + " editada com sucesso.",
                updateCategory
        );
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> deleteById(@PathVariable Integer id) throws NotFoundException {
        ApiResponse<Category> response = new ApiResponse<>();
        service.deleteById(id);
        response.of(
                HttpStatus.OK,
                "Categoria deletada com sucesso."
        );
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}