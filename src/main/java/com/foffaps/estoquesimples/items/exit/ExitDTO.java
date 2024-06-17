package com.foffaps.estoquesimples.items.exit;

import com.foffaps.estoquesimples.items.Items;
import com.foffaps.estoquesimples.person.supplier.Supplier;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ExitDTO {

    private Boolean generateLot;
    private String sku;

    @NotNull(message = "O nome do responsável não pode ficar vazio.")
    private String responsibleName;

    @NotNull(message = "O nome cpf do responsável não pode ficar vazio.")
    private String cpf;

    private Date dateExit;
    private List<ItemExitDTO> items;

    @Data
    public static class ItemExitDTO {
        @NotNull(message = "O lote do item não pode ficar vazio.")
        private String lotNumber;
        private Integer quantity;
        private Long itemId;
        private Long supplierId;
    }
}
