package com.foffaps.estoquesimples.person.supplier;

import com.foffaps.estoquesimples.person.Person;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("SUPPLIER")
@Table(name = "supplier")
public class Supplier extends Person {

    @Column(name = "fantasy_name")
    private String fantasyName;

    @Column(name = "municipal_registration")
    private String municipalRegistration;

    @Column(name = "state_registration")
    private String stateRegistration;

}