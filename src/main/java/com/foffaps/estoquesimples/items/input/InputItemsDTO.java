package com.foffaps.estoquesimples.items.input;

import com.foffaps.estoquesimples.items.items.Items;
import com.foffaps.estoquesimples.person.supplier.Supplier;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InputItemsDTO {

    @NotNull(message = "O campo Lote não pode ficar vazio.")
    private String lotNumber;
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
    private Items item;
}