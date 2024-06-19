package com.foffaps.estoquesimples.items.exit;

import com.foffaps.estoquesimples.items.Items;
import com.foffaps.estoquesimples.items.ItemsRepository;
import com.foffaps.estoquesimples.person.supplier.SupplierRepository;
import com.foffaps.estoquesimples.utils.exceptions.BadRequestException;
import com.foffaps.estoquesimples.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ExitService {

    private final ExitRepository exitRepository;
    private final ItemsRepository itemsRepository;
    private final SupplierRepository supplierRepository;

    @Transactional
    public Exit create(Long itemId, Exit exit) throws NotFoundException, BadRequestException {
        Items item = itemsRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Item não encontrado"));

//        if (entry.getGenerateLot().equals(true)) {
//            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            String dateNow = LocalDate.parse(LocalDate.now().format(format)).toString();
//            String dateFormat = dateNow.replace("-", "");
//            int counter = 1;
//            String lotNumber;
//            do {
//                lotNumber = String.format("%s%03d", "L" + dateFormat + "R", counter++);
//            } while (entryRepository.existsByLotNumber(lotNumber));
//
//            entry.setLotNumber(lotNumber);
//        }

        exit.setDateExit(LocalDate.now());
        exit.setItem(item);
        itemsRepository.findById(itemId).map(
                it -> {
                    if (it.getQtd() - exit.getQuantity() <= 0) {
                        try {
                            throw new BadRequestException("Quantidade de estoque insuficiente");
                        } catch (BadRequestException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    it.setQtd(it.getQtd() - exit.getQuantity());
                    return it;
                }
        );
        return exitRepository.save(exit);
    }

}
