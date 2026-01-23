package com.rudinilly.domain.model.valueobject;

public record Address(String cep, String street, String number, String city, String uf, String complement) {
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

    private void validate(String cep, String street, String number, String city, String uf, String complement) {
        if(cep == null || cep.isBlank()){
            throw new IllegalArgumentException("CEP cannot be empty");
        }

        if(street == null || street.isBlank()){
            throw new IllegalArgumentException("Street cannot be empty");
        }

        if(city == null || city.isBlank()){
            throw new IllegalArgumentException("City cannot be empty");
        }

        if(uf == null || uf.isBlank()){
            throw new IllegalArgumentException("UF cannot be empty");
        }

        if(complement == null || complement.isBlank()){
            throw new IllegalArgumentException("Complement cannot be empty");
        }
    }
}