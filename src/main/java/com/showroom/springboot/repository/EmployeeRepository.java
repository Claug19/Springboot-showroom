package com.showroom.springboot.repository;

import com.showroomSoap.soap.employee.Employee;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class EmployeeRepository {

    private static final Map<Integer, Employee> employees = new HashMap<>();

    @PostConstruct
    public void init(){
        Employee employee = new Employee();
        employee.setId(1);
        employee.setUsername("Suzi");
        employee.setUserPassword("password");
        employee.setUserEmail("suzi@com.innova.com");
        employees.put(employee.getId(), employee);
    }

    public Employee findEmployee(int id){
        return employees.get(id);
    }
}

