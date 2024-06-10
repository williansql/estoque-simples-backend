package com.foffaps.estoquesimples.items.entry;

import com.foffaps.estoquesimples.utils.exceptions.NotFoundException;
import com.foffaps.estoquesimples.utils.models.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("items/entries")
@RequiredArgsConstructor
public class EntryController {

    private final EntryService entryService;

    @PostMapping("/{itemId}")
    public ResponseEntity<ApiResponse<Entry>> createEntry(
            @PathVariable Long itemId, @RequestBody EntryDTO entryDTO) throws NotFoundException {
        ApiResponse<Entry> response = new ApiResponse<>();
        var entry = new Entry();
        BeanUtils.copyProperties(entryDTO, entry);
        Entry save = entryService.createEntry(itemId, entry);
        response.of(
                HttpStatus.CREATED,
                "Entrada criada com sucesso!",
                save);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
