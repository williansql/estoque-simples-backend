package com.foffaps.estoquesimples.address;

import com.foffaps.estoquesimples.utils.exceptions.NotFoundException;
import com.foffaps.estoquesimples.utils.models.PaginatedData;
import com.foffaps.estoquesimples.utils.models.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository repository;
    private final AddressCriteria addressCriteria;


    public Address create(Address address) {
        return repository.save(address);
    }

    public PaginatedData<Address> findAll(AddressCriteria criteria, Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.ASC, "publicPlace"));
        Specification<Address> specification = addressCriteria.createSpecification(criteria);
        Page<Address> pageAddress = repository.findAll(specification, pageable);
        return new PaginatedData<>(pageAddress.getContent(), Pagination.from(pageAddress, pageable));
    }

    public Address update(Long id, Address address) throws NotFoundException {
        return repository.findById(id).map(repository::save).orElseThrow(
                () -> new NotFoundException("Endereço não encontrado."));
    }

    public void deleteById(Long id) throws NotFoundException {
        Optional<Address> findId = repository.findById(id);
        if (findId.isEmpty())
            throw new NotFoundException("Endereço não encontrado.");
        repository.deleteById(id);

    }
}
