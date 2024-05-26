package com.smart.application.controller;

import com.smart.application.models.Employee;
import com.smart.application.services.EmployeeService;
import com.smart.application.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.regex.Pattern.matches;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService, AttendanceService attendanceService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Employee> signup(@RequestBody Employee employee) {
        // Encode the password before saving
        employee.setPassword(employee.getPassword());
        Employee createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @PostMapping("/signin")
    public ResponseEntity<Employee> signin(@RequestParam String username, @RequestParam String password) {
        Employee employee = employeeService.findByUsername(username);
        if (employee != null && matches(password, employee.getPassword())) {
            return ResponseEntity.ok().body(employee);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok().body(employees);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok().body(employee);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }

}
