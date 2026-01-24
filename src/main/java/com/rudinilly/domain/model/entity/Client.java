package com.rudinilly.domain.model.entity;

import com.rudinilly.domain.model.valueobject.Address;
import com.rudinilly.domain.model.valueobject.CPF;
import com.rudinilly.domain.model.valueobject.Email;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

public class Client {
    private UUID id;
    private String fullName;
    private String motherName;
    private Email email;
    private CPF cpf;
    private String rg;
    private String phone;

    private Address address;

    private LocalDate birthDate;
    private LocalDate createdAt;

    public Client(
            String fullName,
            String motherName,
            Email email,
            CPF cpf,
            String rg,
            String phone,
            Address address,
            LocalDate birthDate
    ) {
        validateFullName(fullName);
        validateMotherName(motherName);
        validateEmail(email);
        validateCPF(cpf);
        validateRG(rg);
        validatePhone(phone);
        validateAddress(address);
        validateBirthDate(birthDate);

        this.id = UUID.randomUUID();
        this.fullName = fullName;
        this.motherName = motherName;
        this.email = email;
        this.cpf = cpf;
        this.rg = rg;
        this.phone = phone;
        this.address = address;
        this.birthDate = birthDate;
        this.createdAt = LocalDate.now();
    }

    private void validateFullName(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Full Name cannot be empty");
        }
    }

    private void validateMotherName(String motherName) {
        if (motherName == null || motherName.isBlank()) {
            throw new IllegalArgumentException("Mother Name cannot be empty");
        }
    }

    private void validateEmail(Email email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
    }

    private void validateCPF(CPF cpf) {
        if (cpf == null) {
            throw new IllegalArgumentException("CPF cannot be empty");
        }
    }

    private void validateRG(String rg) {
        if (rg == null || rg.isBlank()) {
            throw new IllegalArgumentException("RG cannot be empty");
        }
    }

    private void validatePhone(String phone) {
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Phone cannot be empty");
        }
    }

    private void validateAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }
    }

    private void validateBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("Birth date cannot be null");
        }

        if (birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birth date cannot be a future date");
        }

        if (Period.between(birthDate, LocalDate.now()).getYears() < 18) {
            throw new IllegalArgumentException("User must be at least 18 years old");
        }
    }

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getMotherName() {
        return motherName;
    }

    public Email getEmail() {
        return email;
    }

    public CPF getCpf() {
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}