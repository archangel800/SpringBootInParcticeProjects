package com.dzidziguri.employeeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class  EmployeeServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(EmployeeServiceApplication.class, args);
        Environment environment = run.getBean(Environment.class);
        System.out.println("DB user: " + environment.getProperty("spring.datasource.username"));
        System.out.println("DB password: " + environment.getProperty("spring.datasource.password"));
    }

}
