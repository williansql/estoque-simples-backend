package com.foffaps.estoquesimples.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryDTO {

    @NotNull(message = "Campo nome não pode ser nulo")
    @NotBlank(message = "Campo nome não pode conter espaços em branco")
    private String name;
}
