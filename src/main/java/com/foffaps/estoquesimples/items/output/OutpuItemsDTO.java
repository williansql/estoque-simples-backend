package com.foffaps.estoquesimples.items.output;

import com.foffaps.estoquesimples.flow.Flow;
import com.foffaps.estoquesimples.items.Items;
import com.foffaps.estoquesimples.person.supplier.Supplier;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OutpuItemsDTO {

    private String sku;
    @NotNull(message = "O nome do responsável não pode ficar vazio.")
    private String responsibleName;
    @NotNull(message = "O nome cpf do responsável não pode ficar vazio.")
    private String responsibleCpf;
    private Double quantity;
    private String lotNumber;
    private LocalDate dateOutput;
    @NotNull(message = "Por favor selecionar um item.")
    private Items item;
    private Supplier supplier;
}