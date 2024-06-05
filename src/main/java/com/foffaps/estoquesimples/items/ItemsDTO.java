package com.foffaps.estoquesimples.items;

import com.foffaps.estoquesimples.category.Category;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ItemsDTO {

    private String codItem;

    @NotNull
    private String name;

    private String model;
    private String branding;
    private String description;
    private Double buyPrice;

    @NotNull
    private Double sellPrice;

    private String sku;
    private String unitMeasureEnum;
    private Double unitMeasureQtd;
    private String statusEnum;
    private Timestamp validationDate;
    private Timestamp manufacturerDate;
    private Timestamp sellDate;
    private Category category;
}
