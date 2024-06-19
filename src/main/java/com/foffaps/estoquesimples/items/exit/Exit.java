package com.foffaps.estoquesimples.items.exit;

import com.foffaps.estoquesimples.items.Items;
import com.foffaps.estoquesimples.person.supplier.Supplier;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "exit_items")
public class Exit {

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
    private String cpf;

    @Column(name = "date_exit")
    private LocalDate dateExit;

    @Column(name = "quantity")
    private Double quantity;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Items item;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;
}
