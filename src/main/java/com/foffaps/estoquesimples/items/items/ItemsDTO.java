package com.foffaps.estoquesimples.items.items;

import com.foffaps.estoquesimples.category.Category;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ItemsDTO {

    private String codItem;

    @NotNull(message = "O nome do item deve ser informado")
    private String name;

    private String model;
    private String branding;
    private String description;
    private Double buyPrice;
    private Double sellPrice;
    private String sku;
    private UnitMeasure unitMeasureEnum;
    private Double unitMeasureQtd;
    private StatusEnum statusEnum;
    private Timestamp validationDate;
    private Timestamp manufacturerDate;
    private Timestamp sellDate;
    private Category category;
}