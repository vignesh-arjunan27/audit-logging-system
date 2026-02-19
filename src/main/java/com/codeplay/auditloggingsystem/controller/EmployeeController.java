package com.codeplay.auditloggingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeplay.auditloggingsystem.entity.Employee;
import com.codeplay.auditloggingsystem.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    // CREATE Employee
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Employee emp) {
        service.create(emp);
        return ResponseEntity.ok("Saved Successfully");
    }

    // GET all employees
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllEmployees() {
        List<Employee> empList = service.getAllEmployees();
        return empList.isEmpty() ? ResponseEntity.status(404).body("No Records Found") : ResponseEntity.ok(empList);
    }

    // GET by ID
    @GetMapping("/find-employee-by-Id/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        Employee emp = service.getEmployeeById(id);
        return ResponseEntity.ok(emp);
    }

    // UPDATE Employee
    @PutMapping("/update-employee-by-Id/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Employee emp) {
        service.update(id, emp);
        return ResponseEntity.ok("Updated Successfully");
    }

    // DELETE Employee
    @DeleteMapping("/delete-employee-by-Id/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted Successfully";
    }
}