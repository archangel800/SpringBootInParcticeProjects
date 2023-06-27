package com.dzidziguri.employeeservice.service;

import com.dzidziguri.employeeservice.exception.EmployeeNotFoundException;
import com.dzidziguri.employeeservice.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    List<Employee> employees = new ArrayList<>();

    @Override
    public Employee save(Employee employee) {
        if(employee.getEmployeeId() == null || employee.getEmailId().isEmpty()){
            employee.setEmployeeId(UUID.randomUUID().toString());
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public Employee getEmployee(String employeeId) {
       return employees.stream()
                .filter(employee -> employee.getEmployeeId().equals(employeeId))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id: " + employeeId + " could not be found"));
    }

    @Override
    public String deleteEmployeeById(String id) {
        Optional<Employee> employee = employees.stream()
                .filter(employee1 -> employee1.getEmployeeId().equals(id))
                .findFirst();
        if (employee.isPresent()) {
            employees.remove(employee.get());
        }
        return "Employee deleted with the id: " + id;
    }
}
