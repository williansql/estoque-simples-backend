package com.foffaps.estoquesimples.items.output;

import com.foffaps.estoquesimples.items.items.Items;
import com.foffaps.estoquesimples.person.supplier.Supplier;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "output")
public class OutputItems {

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

    @Column(name = "date_output")
    private LocalDate dateOutput;

    @Column(name = "quantity")
    private Double quantity;

    @ManyToMany
    @JoinTable(
            name = "output",
            joinColumns = @JoinColumn(name = "input_items_id"),
            inverseJoinColumns = @JoinColumn(name = "items_id"))
    private List<Items> items;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}