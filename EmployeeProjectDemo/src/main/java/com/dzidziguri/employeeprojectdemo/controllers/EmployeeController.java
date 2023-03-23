package com.dzidziguri.employeeprojectdemo.controllers;

import com.dzidziguri.employeeprojectdemo.exceptionHandling.ResourceNotFoundException;
import com.dzidziguri.employeeprojectdemo.model.Employee;
import com.dzidziguri.employeeprojectdemo.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeRepository employeeRepository;

    @GetMapping()
    public ResponseEntity<List<Employee>> getAllEmployee(){
        return new ResponseEntity<>(employeeRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable(name = "id") Long id){
        logger.info("Got Employee From Controller with id {}", id);
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id - " + id));
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody @Valid Employee employee){
        logger.info("Created Employee in Controller ");
        return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody @Valid Employee employee, @PathVariable(name = "id") Long id){
        logger.info("Updated Employee From Controller with id {} and with RequestBOdy {}", id, employee);
        Employee employee1 = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id - " + id));
        employee1.setEmailId(employee.getEmailId());
        employee1.setFirstName(employee1.getFirstName());
        employee1.setEmailId(employee1.getEmailId());
        Employee savedEmp = employeeRepository.save(employee1);
        return new ResponseEntity<>(savedEmp, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable(name = "id") Long id){
        logger.info("Deleted Employee From Controller with id {}", id);
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id - " + id));
        employeeRepository.delete(employee);
        Map<String, Boolean> map = new HashMap<>();
        map.put("deleted", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
