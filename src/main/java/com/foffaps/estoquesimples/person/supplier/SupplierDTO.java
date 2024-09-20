package com.foffaps.estoquesimples.person.supplier;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SupplierDTO {

    @NotNull(message = "O nome do fornecedor não pode ficar vazio.")
    private String name;
    @NotNull(message = "O telefone do fornecedor não pode ficar vazio.")
    private String phone;
    @NotNull(message = "A identidade do fornecedor não pode ficar vazio.")
    private String identity;
    @NotNull(message = "O nome fantasia do fornecedor não pode ficar vazio.")
    private String fantasyName;
}