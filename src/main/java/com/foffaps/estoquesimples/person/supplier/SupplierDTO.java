package com.foffaps.estoquesimples.person.supplier;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SupplierDTO {

    @NotNull
    private String name;
    @NotNull
    private String phone;
    @NotNull
    private String identity;
    @NotNull
    private String fantasyName;
}
