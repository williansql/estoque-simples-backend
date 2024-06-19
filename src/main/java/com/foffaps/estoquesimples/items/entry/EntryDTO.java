package com.foffaps.estoquesimples.items.entry;

import com.foffaps.estoquesimples.items.Items;
import com.foffaps.estoquesimples.person.supplier.Supplier;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class EntryDTO {

    private String lotNumber;
    private Boolean generateLot;

    @NotNull(message = "O nome do responsável não pode ficar vazio.")
    private String responsibleName;
    @NotNull(message = "O nome cpf do responsável não pode ficar vazio.")
    private String responsibleCpf;
    private LocalDateTime dateEntry;
    private String validationDate;
    private String fabricationDate;
    private Double buyPrice;
    private Integer quantity;
    private Supplier supplier;
    private Items items;


}