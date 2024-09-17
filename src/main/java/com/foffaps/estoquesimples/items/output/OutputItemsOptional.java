package com.foffaps.estoquesimples.items.output;


import com.foffaps.estoquesimples.utils.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OutputItemsOptional {

    private OutputItemsRepository repository;

    public String existsByLotNumber(String lotNumber){
        boolean existsByLotNumber = repository.existsByLotNumber(lotNumber);
        if (existsByLotNumber)
            throw new BadRequestException("Já existe um lote com o numero informado: " + lotNumber);
        return null;
    }
}