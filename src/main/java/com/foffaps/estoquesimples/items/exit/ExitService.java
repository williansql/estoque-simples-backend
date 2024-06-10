package com.foffaps.estoquesimples.items.exit;

import com.foffaps.estoquesimples.items.Items;
import com.foffaps.estoquesimples.items.ItemsRepository;
import com.foffaps.estoquesimples.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExitService {

    private final ExitRepository exitRepository;
    private final ItemsRepository itemsRepository;

    @Transactional
    public Exit createExit(Long itemId, Exit exit) throws NotFoundException {
        Items item = itemsRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Item não encontrado"));

        exit.setItem(item);
        return exitRepository.save(exit);
    }
}
