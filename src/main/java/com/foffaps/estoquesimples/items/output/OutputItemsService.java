package com.foffaps.estoquesimples.items.output;

import com.foffaps.estoquesimples.items.Items;
import com.foffaps.estoquesimples.items.ItemsRepository;
import com.foffaps.estoquesimples.person.supplier.SupplierRepository;
import com.foffaps.estoquesimples.utils.exceptions.BadRequestException;
import com.foffaps.estoquesimples.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OutputItemsService {

    private final OutputItemsRepository outputItemsRepository;
    private final ItemsRepository itemsRepository;
    private final SupplierRepository supplierRepository;
    private final OutputItemsOptional optional;

    @Transactional
    public OutputItems create(Long itemID, OutputItems outputItems) throws NotFoundException, BadRequestException {
        List<Items> itemsList = new ArrayList<>();
        Items items = itemsRepository.findById(itemID).orElseThrow(() -> new BadRequestException("Item não encontrado."));
        optional.existsByLotNumber(outputItems.getLotNumber());
        outputItems.setSupplier(outputItems.getSupplier());
        outputItems.set
    }

}