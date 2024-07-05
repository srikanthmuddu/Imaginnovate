package com.example.imaginnovate.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String EmployeeId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;

    @NotEmpty
    private List<String> phoneNumbers;

    @Past
    private LocalDate doj;

    @Min(value = 1)
    private double salary;

    //Getters and Setters

    public @NotBlank String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(@NotBlank String employeeId) {
        EmployeeId = employeeId;
    }

    public @NotBlank String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank String email) {
        this.email = email;
    }



    public @Past LocalDate getDoj() {
        return doj;
    }

    public void setDoj(@Past LocalDate doj) {
        this.doj = doj;
    }

    public @Min(value = 1) Double getSalary() {
        return salary;
    }


    public void setSalary(@Min(value = 1) Double salary) {
        this.salary = salary;
    }

    public @NotEmpty List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(@NotEmpty List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }


}

