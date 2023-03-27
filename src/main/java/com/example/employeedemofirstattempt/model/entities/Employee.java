package com.example.employeedemofirstattempt.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@Getter
@Setter
public class Employee implements Serializable {
    @Serial
    private static final long serialVersionUID = 6242880703413649349L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;
    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @Email
    @Column(name = "email")
    private String email;

    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
