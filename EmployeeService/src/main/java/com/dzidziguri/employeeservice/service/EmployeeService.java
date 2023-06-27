package com.dzidziguri.employeeservice.service;

import com.dzidziguri.employeeservice.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee save(Employee employee);

    List<Employee> getEmployees();

    Employee getEmployee(String employeeId);

    String deleteEmployeeById(String id);
}
