package com.foffaps.estoquesimples.items.exit;

import com.foffaps.estoquesimples.flow.Flow;
import com.foffaps.estoquesimples.items.Items;
import com.foffaps.estoquesimples.items.entry.Entry;
import com.foffaps.estoquesimples.items.entry.EntryDTO;
import com.foffaps.estoquesimples.utils.exceptions.BadRequestException;
import com.foffaps.estoquesimples.utils.exceptions.NotFoundException;
import com.foffaps.estoquesimples.utils.models.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("exits")
@RequiredArgsConstructor
public class ExitController {

    private final ExitService exitService;

    @PostMapping("/{itemId}")
    public ResponseEntity<ApiResponse<Exit>> createEntry(
            @PathVariable Long itemId, @RequestBody ExitDTO exitDTO) throws NotFoundException, BadRequestException {
        ApiResponse<Exit> response = new ApiResponse<>();
        var exit = new Exit();
        BeanUtils.copyProperties(exitDTO, exit);
        Exit save = exitService.create(itemId, exit);
        response.of(
                HttpStatus.CREATED,
                save.getItem().getName().toUpperCase() + ", QTD: " + save.getQuantity(),
                save);
        Flow flow = new Flow();
        flow.setExit(exit);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
