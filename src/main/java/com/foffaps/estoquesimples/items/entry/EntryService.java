package com.foffaps.estoquesimples.items.entry;

import com.foffaps.estoquesimples.items.Items;
import com.foffaps.estoquesimples.items.ItemsRepository;
import com.foffaps.estoquesimples.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class EntryService {

    private final EntryRepository entryRepository;
    private final ItemsRepository itemsRepository;

    @Transactional
    public Entry createEntry(Long itemId, Entry entry) throws NotFoundException {
        Items item = itemsRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Item não encontrado"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate.parse(entry.getFabricationDate(), formatter);
        LocalDate.parse(entry.getValidationDate(), formatter);
        entry.setDateEntry(LocalDateTime.now());
        entry.setItem(item);
        itemsRepository.findById(itemId).map(
                it -> {
                    it.setQtd(it.getQtd() + entry.getQuantity());
                    it.setBuyPrice(entry.getBuyPrice());
                    return it;
                }
        );
        return entryRepository.save(entry);
    }
}