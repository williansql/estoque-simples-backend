package com.foffaps.estoquesimples.items;

import com.foffaps.estoquesimples.category.Category;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "items")
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cod_item")
    private String codItem;

    @Column(name = "name")
    private String name;

    @Column(name = "model")
    private String model;

    @Column(name = "branding")
    private String branding;

    @Column(name = "description")
    private String description;

    @Column(name = "buy_price")
    private Double buyPrice;

    @Column(name = "sell_price")
    private Double sellPrice;

    @Column(name = "sku")
    private String sku;

    @Column(name = "unit_measure")
    private String unitMeasureEnum;

    @Column(name = "unit_measure_qtd")
    private Double unitMeasureQtd;

    @Column(name = "status")
    private String statusEnum;

    @Column(name = "qtd")
    private Double qtd;

    @Column(name = "validation_date")
    private Timestamp validationDate;

    @Column(name = "manufacturer_date")
    private Timestamp manufacturerDate;

    @Column(name = "sell_date")
    private Timestamp sellDate;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
