package com.foffaps.estoquesimples.items.entry;

import com.foffaps.estoquesimples.flow.Flow;
import com.foffaps.estoquesimples.flow.FlowRepository;
import com.foffaps.estoquesimples.items.Items;
import com.foffaps.estoquesimples.items.ItemsRepository;
import com.foffaps.estoquesimples.items.lot.Lot;
import com.foffaps.estoquesimples.items.lot.LotRepository;
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
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class EntryService {

    private final EntryRepository entryRepository;
    private final ItemsRepository itemsRepository;
    private final FlowRepository flowRepository;
    private final EntryCriteria entryCriteria;
    private final LotRepository lotRepository;

    @Transactional
    public Entry createEntry(Long itemId, Entry entry) throws NotFoundException {
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (entry.getFabricationDate() != null) {
            LocalDate.parse(entry.getFabricationDate().formatted(formatter));
        }
        if (entry.getValidationDate() != null) {
            LocalDate.parse(entry.getValidationDate().formatted(formatter));
        }
        entry.setDateEntry(LocalDateTime.now());
        entry.setItem(item);
        itemsRepository.findById(itemId).map(
                it -> {
                    it.setQtd(it.getQtd() + entry.getQuantity());
                    it.setBuyPrice(entry.getBuyPrice());
                    return it;
                }
        );
        LocalDateTime date = LocalDateTime.now();
        Flow flow = new Flow();
        flow.setEntry(entry);
        flow.setItem(entry.getItem());
        flow.setOperation("ENTRADA");
        flowRepository.save(flow);

        Lot lot = new Lot();
        lot.setDate(date);
        lot.setTypeOperation("NOVO_LOTE");
        lot.setLotNumber(entry.getLotNumber());
        lot.setEntry(entry);
        lotRepository.save(lot);

        return entryRepository.save(entry);
    }

    public PaginatedData<Entry> findAll(EntryCriteria criteria, Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.ASC, "lotNumber"));
        Specification<Entry> specification = entryCriteria.createSpecification(criteria);
        Page<Entry> pageEntry = entryRepository.findAll(specification, pageable);
        return new PaginatedData<>(pageEntry.getContent(), Pagination.from(pageEntry, pageable));
    }
}