package com.rudinilly.domain.model.valueobject;

import com.rudinilly.domain.model.enums.UF;

public record Address(String cep, String street, String number, String city, UF uf, String complement) {
    public Address {
        validate(
                cep,
                street,
                number,
                city,
                uf,
                complement
        );
    }

    private void validate(String cep, String street, String number, String city, UF uf, String complement) {
        if(cep == null || cep.isBlank()){
            throw new IllegalArgumentException("CEP cannot be empty");
        }

        if (!cep.matches("\\d{5}-?\\d{3}")) {
            throw new IllegalArgumentException("Invalid CEP format");
        }

        if(street == null || street.isBlank()){
            throw new IllegalArgumentException("Street cannot be empty");
        }

        if (street.length() > 150) {
            throw new IllegalArgumentException("Street is too long");
        }

        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException("Number cannot be empty");
        }

        if (number.length() > 10) {
            throw new IllegalArgumentException("Number is too long");
        }

        if (!number.matches("[0-9A-Za-z]+")) {
            throw new IllegalArgumentException("Number contains invalid characters");
        }

        if(city == null || city.isBlank()){
            throw new IllegalArgumentException("City cannot be empty");
        }

        if (city.length() > 100) {
            throw new IllegalArgumentException("City is too long");
        }

        if(uf == null){
            throw new IllegalArgumentException("UF cannot be null");
        }

        if (complement != null && complement.length() > 100) {
            throw new IllegalArgumentException("Complement is too long");
        }
    }
}