package com.foffaps.estoquesimples.address;

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

import java.util.stream.Stream;

@RestController
@RequestMapping("address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService service;

    @PostMapping
    public ResponseEntity<ApiResponse<Address>> create (
            @Valid @RequestBody AddressDTO addressDTO) throws BadRequestException {
        ApiResponse<Address> response = new ApiResponse<>();
        var address = new Address();
        BeanUtils.copyProperties(addressDTO, address);
        Address saved = service.create(address);
        response.of(HttpStatus.CREATED,
                "Endereço criado com sucesso " + saved.getPublicPlace().toUpperCase());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginatedData<Address>>> findAll(
            AddressCriteria criteria, @PageableDefault(size = 5) Pageable pageable){
        ApiResponse<PaginatedData<Address>> response = new ApiResponse<>();
        PaginatedData<Address> pageAddress = service.findAll(criteria, pageable);
        response.of(
                HttpStatus.OK,
                "Lista de endereços.",
                pageAddress);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Address>> update(
            @PathVariable Long id, @RequestBody AddressDTO addressDTO)
            throws BadRequestException, NotFoundException {
        ApiResponse<Address> response = new ApiResponse<>();
        var address = new Address();
        BeanUtils.copyProperties(addressDTO, address);
        Address updateAddress = service.update(id, address);
        response.of(
                HttpStatus.OK,
                "Endereço atualizado. " + updateAddress.getPublicPlace().toUpperCase(),
                updateAddress);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Address>> deleteById(
            @PathVariable Long id)throws NotFoundException{
        ApiResponse<Address> response = new ApiResponse<>();
        service.deleteById(id);
        response.of(
                HttpStatus.OK,
                "Endereço deletado.");
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
