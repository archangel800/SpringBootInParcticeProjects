package com.dzidziguri.loggingapplication.loggingspringapplication.controller;

import com.dzidziguri.loggingapplication.loggingspringapplication.ResourceNotFoundException;
import com.dzidziguri.loggingapplication.loggingspringapplication.model.Employee;
import com.dzidziguri.loggingapplication.loggingspringapplication.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employee")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee does not exist with id :" + id));
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) throws ResourceNotFoundException {
        Employee employeeFound = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee does not exist with id :" + id));
        employeeFound.setFirstName(employee.getFirstName());
        employeeFound.setLastName(employee.getLastName());
        employeeFound.setEmail(employee.getEmail());

        Employee updateEmployee = employeeRepository.save(employeeFound);
        return ResponseEntity.ok(updateEmployee);
    }
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) throws ResourceNotFoundException {
        Employee employeeFound = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id :" + id));
        employeeRepository.delete(employeeFound);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return ResponseEntity.ok(response);
    }
}
