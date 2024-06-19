package com.foffaps.estoquesimples.items.lot;

import com.foffaps.estoquesimples.utils.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LotService {

    private final LotRepository lotRepository;


    public Lot createLot(Lot lot) throws BadRequestException {
        Lot save = lotRepository.save(lot);
        if (save.equals(null)){
            throw new BadRequestException("Erro ao salvar o lote.");
        }
        return save;
    }
}
