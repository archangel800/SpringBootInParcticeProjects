package com.dzidziguri.employeeprojectdemo;

import com.dzidziguri.employeeprojectdemo.model.Employee;
import com.dzidziguri.employeeprojectdemo.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationStartedEventListener implements ApplicationListener {
    private final Logger logger = LoggerFactory.getLogger(ApplicationStartedEventListener.class);
    private final EmployeeRepository employeeRepository;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
    logger.info("In ApplicationStartedEventListener");
        Employee emp1 = new Employee();
        emp1.setFirstName("John");
        emp1.setLastName("Doe");
        emp1.setEmailId("johndoe@example.com");

        Employee emp2 = new Employee();
        emp2.setFirstName("Jane");
        emp2.setLastName("Doe");
        emp2.setEmailId("janedoe@example.com");

        Employee emp3 = new Employee();
        emp3.setFirstName("Bob");
        emp3.setLastName("Smith");
        emp3.setEmailId("bobsmith@example.com");

        Employee emp4 = new Employee();
        emp4.setFirstName("Alice");
        emp4.setLastName("Smith");
        emp4.setEmailId("alicesmith@example.com");

        Employee emp5 = new Employee();
        emp5.setFirstName("Tom");
        emp5.setLastName("Jones");
        emp5.setEmailId("tomjones@example.com");
        employeeRepository.save(emp1);
        employeeRepository.save(emp2);
        employeeRepository.save(emp3);
        employeeRepository.save(emp4);
        employeeRepository.save(emp5);
    }

}
