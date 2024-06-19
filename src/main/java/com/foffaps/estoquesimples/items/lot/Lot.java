package com.foffaps.estoquesimples.items.lot;

import com.foffaps.estoquesimples.items.entry.Entry;
import com.foffaps.estoquesimples.items.exit.Exit;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "lot")
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lot_number")
    private String lotNumber;

    @Column(name = "generate_lot")
    private Boolean generateLot;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "type_operation")
    private String typeOperation;

    @ManyToOne
    @JoinColumn(name = "entry_id")
    private Entry entry;

    @ManyToOne
    @JoinColumn(name = "exit_id")
    private Exit exit;
}
