package com.dzidziguri.loggingapplication.loggingspringapplication.commandlinerunner;

import com.dzidziguri.loggingapplication.loggingspringapplication.model.Employee;
import com.dzidziguri.loggingapplication.loggingspringapplication.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ScriptForDatabase implements CommandLineRunner {
    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public void run(String... args) throws Exception {
        Employee employee = new Employee();
        employee.setEmail("matedzidziguri29@gmail.com");
        employee.setFirstName("mate");
        employee.setLastName("dzidziguri");
        employeeRepository.save(employee);
    }
}
