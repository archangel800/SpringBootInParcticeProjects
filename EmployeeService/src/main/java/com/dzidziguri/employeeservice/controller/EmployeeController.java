package com.dzidziguri.employeeservice.controller;

import com.dzidziguri.employeeservice.model.Employee;
import com.dzidziguri.employeeservice.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Qualifier("employeeServiceImpl")
    @Autowired
    EmployeeService employeeService;

    @PostMapping()
    public Employee save(@RequestBody @Valid Employee employee) {
        return employeeService.save(employee);
    }
    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable String id) {
        return employeeService.getEmployee(id);
    }

    @DeleteMapping("{id}")
    public String deleteEMployeeById(@PathVariable String id) {
        return employeeService.deleteEmployeeById(id);
    }
}
