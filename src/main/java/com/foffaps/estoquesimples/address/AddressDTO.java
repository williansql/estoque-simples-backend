package com.foffaps.estoquesimples.address;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressDTO {

    @NotNull(message = "O campo endereço não pode ficar vazio")
    private String publicPlace;
    private String number;
    @NotNull(message = "O campo bairro não pode ficar vazio")
    private String district;
    @NotNull(message = "O campo estado não pode ficar vazio")
    private String state;
    @NotNull(message = "O campo cidade não pode ficar vazio")
    private String city;
    @NotNull(message = "O campo país não pode ficar vazio")
    private String country;
    private String zipCode;
}
