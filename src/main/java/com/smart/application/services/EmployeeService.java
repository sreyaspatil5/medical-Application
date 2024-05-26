package com.smart.application.services;

import com.smart.application.models.Attendance;
import com.smart.application.models.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long employeeId);

    void deleteEmployee(Long employeeId);

    Employee findByUsername(String username);
}
