package com.foffaps.estoquesimples.items.entry;

import com.foffaps.estoquesimples.items.Items;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "entry")
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "responsible_name")
    private String responsibleName;

    @Column(name = "responsible_cpf")
    private String cpf;

    @Column(name = "date_entry")
    private Date dateEntry;

    @Column(name = "validation_date")
    private Date validationDate;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Items item;
}
