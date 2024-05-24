package com.raulcrespo.springboot.cruddemo.rest;

import com.raulcrespo.springboot.cruddemo.entity.Employee;
import com.raulcrespo.springboot.cruddemo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;

    // injecting employee service (constructor dependency injection)
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);

        if(employee == null) {
            throw new RuntimeException("Employee not found. Id - " + employeeId);
        }

        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        // set id to 0 to force a save of new item
        // instead of updating an existing one
        employee.setId(0);

        Employee dbEmployee = employeeService.save(employee);

        // return the database employee instead of the requested one
        return dbEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        Employee dbEmployee = employeeService.save(employee);

        return dbEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee tempEmployee = employeeService.findById(employeeId);

        if (tempEmployee == null) {
            throw new RuntimeException("Employee not found. Id - " + employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee with id: " + employeeId;
    }
}
