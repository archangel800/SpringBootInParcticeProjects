package com.dzidziguri.employeeprojectdemo.model;

import com.dzidziguri.employeeprojectdemo.validation.ValidEmail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false)
    @NotBlank
    private String firstName;
    @Column(name = "last_name", nullable = false)
    @NotBlank
    private String lastName;
    @Column(name = "email_id", nullable = false)
    @NotBlank
    @ValidEmail
    private String emailId;
}
