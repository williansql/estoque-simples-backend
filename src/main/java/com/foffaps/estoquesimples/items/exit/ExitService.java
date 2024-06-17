package com.foffaps.estoquesimples.items.exit;

import com.foffaps.estoquesimples.items.Items;
import com.foffaps.estoquesimples.items.ItemsRepository;
import com.foffaps.estoquesimples.person.supplier.Supplier;
import com.foffaps.estoquesimples.person.supplier.SupplierRepository;
import com.foffaps.estoquesimples.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExitService {

    private final ExitRepository exitRepository;
    private final ItemsRepository itemsRepository;
    private final SupplierRepository supplierRepository;

    @Transactional
    public List<Exit> createExits(List<ExitDTO.ItemExitDTO> itemExitDTOs, ExitDTO exitDTO) throws NotFoundException {
        List<Exit> exits = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        for (ExitDTO.ItemExitDTO itemExitDTO : itemExitDTOs) {
            Items item = itemsRepository.findById(itemExitDTO.getItemId())
                    .orElseThrow(() -> new NotFoundException("Item não encontrado"));
            Supplier supplier = supplierRepository.findById(itemExitDTO.getSupplierId())
                    .orElseThrow(() -> new NotFoundException("Fornecedor não encontrado"));

            Exit exit = new Exit();
            exit.setGenerateLot(exitDTO.getGenerateLot());
            exit.setSku(exitDTO.getSku());
            exit.setResponsibleName(exitDTO.getResponsibleName());
            exit.setCpf(exitDTO.getCpf());
            exit.setDateExit(exitDTO.getDateExit());
            exit.setQuantity(itemExitDTO.getQuantity());
            exit.setItem(item);
            exit.setSupplier(supplier);

            if (exitDTO.getGenerateLot()) {
                String dateFormated = LocalDate.now().format(formatter);
                Long counter = 1L;
                String lotNumber;
                do {
                    lotNumber = String.format("%s%03d", "L" + dateFormated + "S", counter++);
                } while (exitRepository.existsByLotNumber(lotNumber));
                exit.setLotNumber(lotNumber);
            } else {
                exit.setLotNumber(itemExitDTO.getLotNumber());
            }
            exits.add(exitRepository.save(exit));
        }
        return exits;
    }
}
