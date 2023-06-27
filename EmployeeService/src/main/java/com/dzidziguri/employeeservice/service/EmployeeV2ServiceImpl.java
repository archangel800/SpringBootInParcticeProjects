package com.dzidziguri.employeeservice.service;

import com.dzidziguri.employeeservice.entity.EmployeeEntity;
import com.dzidziguri.employeeservice.model.Employee;
import com.dzidziguri.employeeservice.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeV2ServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee employee) {
        if(employee.getEmployeeId() == null || employee.getEmailId().isEmpty()){
            employee.setEmployeeId(UUID.randomUUID().toString());
        }
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepository.save(employeeEntity);
        return employee;
    }

    @Override
    public List<Employee> getEmployees() {

        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        List<Employee> employees = employeeEntities.stream().map(item -> {
            Employee employee = new Employee();
            BeanUtils.copyProperties(item, employee);
            return employee;
                })
                .collect(Collectors.toList());
        return employees;
    }

    @Override
    public Employee getEmployee(String employeeId) {
        EmployeeEntity employee = employeeRepository.findById(employeeId).get();
        Employee employee1 = new Employee();
        BeanUtils.copyProperties(employee, employee1);
        return employee1;
    }

    @Override
    public String deleteEmployeeById(String id) {
        employeeRepository.deleteById(id);
        return "Employee deleted by the id: " + id;
    }
}
