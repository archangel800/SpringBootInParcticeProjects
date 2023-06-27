package com.dzidziguri.employeeservice.model;

import com.dzidziguri.employeeservice.validation.ValidEmail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Employee {

    private String employeeId;
    private String firstName;
    private String lastName;
    @ValidEmail
    private String emailId;

    //@JsonIgnore
    private String department;


}
