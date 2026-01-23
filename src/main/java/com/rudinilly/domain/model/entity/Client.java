package com.rudinilly.domain.model.entity;

import com.rudinilly.domain.model.valueobject.Address;

import java.time.LocalDate;
import java.util.UUID;

public class Client {
    private UUID id;
    private String fullName;
    private String motherName;
    private String email;
    private String cpf;
    private String rg;
    private String phone;

    private Address address;

    private LocalDate birthDate;
    private LocalDate registryDate;

    public Client(
            UUID id,
            String fullName,
            String motherName,
            String email,
            String cpf,
            String rg,
            String phone,
            Address address,
            LocalDate birthDate,
            LocalDate registryDate
    ) {
        validateId(id);
        validateFullName(fullName);
        validadeMotherName(motherName);
        validateEmail(email);
        validateCPF(cpf);
        validateRG(rg);
        validatePhone(phone);
        validateAddress(address);
        validateBirthDate(birthDate);
        validateRegistryDate(registryDate);

        this.id = id;
        this.fullName = fullName;
        this.motherName = motherName;
        this.email = email;
        this.cpf = cpf;
        this.rg = rg;
        this.phone = phone;
        this.address = address;
        this.birthDate = birthDate;
        this.registryDate = registryDate;
    }

    private void validateId(UUID id) {}

    private void validateFullName(String fullName) {}

    private void validadeMotherName(String motherName) {}

    private void validateEmail(String email) {}

    private void validateCPF(String cpf) {}

    private void validateRG(String rg) {}

    private void validatePhone(String phone) {}

    private void validateAddress(Address address) {}

    private void validateBirthDate(LocalDate birthDate) {}

    private void validateRegistryDate(LocalDate registryDate) {}

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public String getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDate getRegistryDate() {
        return registryDate;
    }
}