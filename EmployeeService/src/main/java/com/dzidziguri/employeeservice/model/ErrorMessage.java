package com.dzidziguri.employeeservice.model;

import lombok.Data;
import lombok.ToString;
import org.apache.logging.log4j.message.LocalizedMessage;

import java.time.LocalDate;

@Data
@ToString
public class ErrorMessage {
    private String message;
    private LocalDate time;
    private String localizedMessage;
}
