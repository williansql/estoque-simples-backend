package com.foffaps.estoquesimples.items.input;

import com.foffaps.estoquesimples.items.items.Items;
import com.foffaps.estoquesimples.person.supplier.Supplier;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "input")
public class InputItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lot_number")
    private String lotNumber;

    @Column(name = "sku")
    private String sku;

    @Column(name = "responsible_name")
    private String responsibleName;

    @Column(name = "responsible_cpf")
    private String responsibleCpf;

    @Column(name = "date_entry")
    private LocalDateTime dateEntry;

    @Column(name = "validation_date")
    private String validationDate;

    @Column(name = "fabrication_date")
    private String fabricationDate;

    @Column(name = "buy_price")
    private Double buyPrice;

    @Column(name = "sell_price")
    private Double sellPrice;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToMany
    @JoinTable(name = "output")
    private List<Items> items;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}