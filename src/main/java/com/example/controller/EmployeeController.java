package com.example.controller;

import com.example.entity.Employees;
import com.example.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("all-employees")
    public List<Employees> saveEmployees(){
        return employeeService.allEmployees();
    }

    @PostMapping("save-employees")
    public Employees saveEmployee(@RequestBody Employees employees){
        return employeeService.saveEmployee(employees);

    }
}
