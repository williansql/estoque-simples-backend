package com.foffaps.estoquesimples.flow;

import com.foffaps.estoquesimples.category.Category;
import com.foffaps.estoquesimples.items.Items;
import com.foffaps.estoquesimples.items.entry.Entry;
import com.foffaps.estoquesimples.items.exit.Exit;
import com.foffaps.estoquesimples.person.supplier.Supplier;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "flow")
public class Flow {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "operation")
    private String operation;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Items item;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "entry_id")
    private Entry entry;

    @ManyToOne
    @JoinColumn(name = "exit_id")
    private Exit exit;
}



