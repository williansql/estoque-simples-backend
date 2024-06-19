package com.foffaps.estoquesimples.items.lot;

import com.foffaps.estoquesimples.utils.models.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lot")
@RequiredArgsConstructor
public class LotController {

    private final LotService lotService;


}
