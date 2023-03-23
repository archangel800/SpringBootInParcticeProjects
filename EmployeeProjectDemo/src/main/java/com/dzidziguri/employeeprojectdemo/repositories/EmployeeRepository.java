package com.dzidziguri.employeeprojectdemo.repositories;

import com.dzidziguri.employeeprojectdemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
