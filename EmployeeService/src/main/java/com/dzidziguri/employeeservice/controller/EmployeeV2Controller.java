package com.dzidziguri.employeeservice.controller;

import com.dzidziguri.employeeservice.model.Employee;
import com.dzidziguri.employeeservice.service.EmployeeService;
import com.dzidziguri.employeeservice.service.EmployeeServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/employees")
@RequiredArgsConstructor
public class EmployeeV2Controller {

    @Qualifier("employeeV2ServiceImpl")
 private final EmployeeService employeeV2ServiceImpl;

    @PostMapping
    public Employee save(@RequestBody @Valid Employee employee) {
        return employeeV2ServiceImpl.save(employee);
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable String id) {
        return employeeV2ServiceImpl.getEmployee(id);
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeV2ServiceImpl.getEmployees();
    }
    @DeleteMapping("{id}")
    public String deleteEmployee(@PathVariable String id) {
        return employeeV2ServiceImpl.deleteEmployeeById(id);
    }


}
