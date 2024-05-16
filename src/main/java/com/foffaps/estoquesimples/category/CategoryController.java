package com.foffaps.estoquesimples.category;

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
@RequiredArgsConstructor
@RequestMapping("category")
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService service;


    @PostMapping
    public ResponseEntity<ApiResponse<Category>> create (@RequestBody CategoryDTO categoryDTO) throws BadRequestException {
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
            CategoryCriteria categoryCriteria, @PageableDefault(size = 5) Pageable pageable){
        ApiResponse<PaginatedData<Category>> response = new ApiResponse<>();
        PaginatedData<Category> getCategory = service.findAllCategory(categoryCriteria, pageable);
        response.of(
                HttpStatus.FOUND,
                "Categorias encontrada.",
                getCategory
        );
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> findById(
            @PathVariable String id) throws NotFoundException {
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
            @PathVariable String id, @RequestPart CategoryDTO categoryDTO) throws NotFoundException, BadRequestException{
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
    public ResponseEntity<ApiResponse<Category>> deleteById(@PathVariable String id) throws NotFoundException {
        ApiResponse<Category> response = new ApiResponse<>();
        service.deleteById(id);
        response.of(
                HttpStatus.OK,
                "Categoria deletada com sucesso."
        );
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
