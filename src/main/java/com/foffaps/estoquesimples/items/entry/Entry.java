package com.foffaps.estoquesimples.items.entry;

import com.foffaps.estoquesimples.items.Items;
import com.foffaps.estoquesimples.person.supplier.Supplier;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "entry")
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lot_number")
    private String lotNumber;

    @Column(name = "generate_lot")
    private Boolean generateLot;

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

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Items item;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;
}