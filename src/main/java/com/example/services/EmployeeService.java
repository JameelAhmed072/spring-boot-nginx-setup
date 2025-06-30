package com.example.services;

import com.example.entity.Employees;
import com.example.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employees saveEmployee(Employees employees){
        employees.setServerPort(9002);
        employees.setCreatedAt(LocalDateTime.now());
        return employeeRepository.save(employees);
    }

    public List<Employees> allEmployees(){
        return employeeRepository.findAll();
    }
}
