package com.foffaps.estoquesimples.items.exit;

import com.foffaps.estoquesimples.items.Items;
import com.foffaps.estoquesimples.person.supplier.Supplier;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ExitDTO {

    @NotNull(message = "O nome do responsável não pode ficar vazio.")
    private String responsibleName;

    @NotNull(message = "O nome cpf do responsável não pode ficar vazio.")
    private String cpf;

    private Date dateExit;
    private Integer quantity;
    private List<Items> items;
    private Supplier supplier;
}
