package com.example.springconcept;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan("com.example.dependencyinjection")
public class DependencyInjectionApplication implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DependencyInjectionApplication.class);

    @Autowired
    private EmployeeBean employee;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DependencyInjectionApplication.class, args);
        EmployeeBean emp = context.getBean(EmployeeBean.class);
        logger.info("Employee Details: {}", emp);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Application Started Successfully");
    }
}

@Component
class EmployeeBean {
    private final DepartmentBean department;

    @Autowired
    public EmployeeBean(DepartmentBean department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee belongs to: " + department.getDepartmentName();
    }
}

@Component
class DepartmentBean {
    public String getDepartmentName() {
        return "Software Development";
    }
}