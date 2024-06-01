package com.foffaps.estoquesimples.subcategory;

import com.foffaps.estoquesimples.category.Category;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
