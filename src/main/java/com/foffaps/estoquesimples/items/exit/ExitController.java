package com.foffaps.estoquesimples.items.exit;

import com.foffaps.estoquesimples.utils.exceptions.NotFoundException;
import com.foffaps.estoquesimples.utils.models.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("exits")
@RequiredArgsConstructor
public class ExitController {

    private final ExitService exitService;

    @PostMapping("/{itemId}")
    public ResponseEntity<ApiResponse<Exit>> createExit(
            @PathVariable Long itemId, @RequestBody ExitDTO exitDTO) throws NotFoundException {
        ApiResponse<Exit> response = new ApiResponse<>();
        var exit = new Exit();
        BeanUtils.copyProperties(exitDTO, exit);
        Exit save = exitService.createExit(itemId, exit);
        response.of(
                HttpStatus.CREATED,
                "Saída criada com sucesso!",
                save);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
