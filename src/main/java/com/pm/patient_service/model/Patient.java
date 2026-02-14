package com.pm.patient_service.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Patient {
    //Databases table
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    private String address;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private LocalDate registered_date;

    //Getter
    public @NotNull String getAddress() {
        return address;
    }

    public @NotNull @Email String getEmail() {
        return email;
    }

    public @NotNull String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public @NotNull LocalDate getBirthDate() {
        return birthDate;
    }

    public @NotNull LocalDate getRegisteredDate() {
        return registered_date;
    }

    //Setters

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public void setEmail(@NotNull @Email String email) {
        this.email = email;
    }

    public void setAddress(@NotNull String address) {
        this.address = address;
    }

    public void setBirthDate(@NotNull LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setRegisteredDate(@NotNull LocalDate registered_date) {
        this.registered_date = registered_date;
    }
}
