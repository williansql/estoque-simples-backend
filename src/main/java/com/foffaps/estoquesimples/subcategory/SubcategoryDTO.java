package com.foffaps.estoquesimples.subcategory;

import com.foffaps.estoquesimples.category.Category;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubcategoryDTO {

    @NotNull(message = "O campo nome não pode ficar vazio")
    private String name;

    private Category category;
}
