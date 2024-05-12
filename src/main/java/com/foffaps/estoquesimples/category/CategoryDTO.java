package com.foffaps.estoquesimples.category;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryDTO {

    @NotNull(message = "Campo nome não pode ficar vazio")
    private String name;
}
