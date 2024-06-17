package com.foffaps.estoquesimples.items.exit;

import com.foffaps.estoquesimples.utils.exceptions.NotFoundException;
import com.foffaps.estoquesimples.utils.models.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("exits")
@RequiredArgsConstructor
public class ExitController {

    private final ExitService exitService;

    @PostMapping
    public ResponseEntity<ApiResponse<List<Exit>>> createExits(@RequestBody ExitDTO exitDTO) throws NotFoundException {
        ApiResponse<List<Exit>> response = new ApiResponse<>();
        List<Exit> saves = exitService.createExits(exitDTO.getItems(), exitDTO);
        response.of(
                HttpStatus.CREATED,
                "Saídas criadas com sucesso!",
                saves);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
