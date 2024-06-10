package com.foffaps.estoquesimples.items.entry;

import com.foffaps.estoquesimples.items.Items;
import com.foffaps.estoquesimples.person.supplier.Supplier;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EntryDTO {

    @NotNull(message = "O nome do responsável não pode ficar vazio.")
    private String responsibleName;
    @NotNull(message = "O nome cpf do responsável não pode ficar vazio.")
    private String cpf;
    private Date dateEntry;
    private Date validationDate;
    private Integer quantity;
    private Items items;

}
