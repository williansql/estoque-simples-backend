package com.foffaps.estoquesimples.items.exit;

import com.foffaps.estoquesimples.flow.Flow;
import com.foffaps.estoquesimples.items.Items;
import com.foffaps.estoquesimples.person.supplier.Supplier;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class ExitDTO {

    private String sku;
    @NotNull(message = "O nome do responsável não pode ficar vazio.")
    private String responsibleName;
    @NotNull(message = "O nome cpf do responsável não pode ficar vazio.")
    private String cpf;
    private Double quantity;
    private String lotNumber;
    private LocalDate dateExit;
    private Items item;
    private Supplier supplier;
    private Flow flow;
}
