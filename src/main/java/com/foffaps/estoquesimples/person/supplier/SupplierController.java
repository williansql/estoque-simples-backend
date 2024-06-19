package com.foffaps.estoquesimples.person.supplier;


import com.foffaps.estoquesimples.flow.Flow;
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
@RequestMapping("supplier")
@RequiredArgsConstructor

public class SupplierController {

    private final SupplierService service;

    @PostMapping
    public ResponseEntity<ApiResponse<Supplier>> create (
            @RequestBody @Valid SupplierDTO supplierDTO) throws BadRequestException {
        ApiResponse<Supplier> response = new ApiResponse<>();
        var supplier = new Supplier();
        BeanUtils.copyProperties(supplierDTO, supplier);
        Supplier saved = service.create(supplier);
        response.of(
                HttpStatus.CREATED,
                "Fornecedor " +
                        saved.getFantasyName().toUpperCase() +
                        " criado.",
                saved
        );
        Flow flow = new Flow();
        flow.setSupplier(supplier);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginatedData<Supplier>>> findAll(
            SupplierCriteria criteria, @PageableDefault(size = 5) Pageable pageable){
            ApiResponse<PaginatedData<Supplier>> response = new ApiResponse<>();
            PaginatedData<Supplier> findSuppliers = service.findAll(criteria, pageable);
            response.of(
                    HttpStatus.OK,
                    "Lista de fornecedores",
                    findSuppliers
            );
            return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Supplier>> update(
            @PathVariable Long id, @RequestBody SupplierDTO supplierDTO)
            throws BadRequestException, NotFoundException {
        ApiResponse<Supplier> response = new ApiResponse<>();
        var supplier = new Supplier();
        BeanUtils.copyProperties(supplierDTO, supplier);
        Supplier saved = service.update(id, supplier);
        response.of(
                HttpStatus.OK,
                "Fornecedor " +
                    saved.getFantasyName().toUpperCase() +
                        " editado com sucesso.",
                saved
        );
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Supplier>> deleteById(@PathVariable Long id)
            throws NotFoundException {
        ApiResponse<Supplier> response = new ApiResponse<>();
        service.deleteById(id);
        response.of(
                HttpStatus.OK,
                "Fornecedor deletado com sucesso "
        );
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
