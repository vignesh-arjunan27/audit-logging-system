/*
 😉Author      : Vignesh Arjunan
 💻Role        : Software Engineer
 🐦‍🔥Created On  : 20-02-2026 00:25
 */

package com.codeplay.auditloggingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeplay.auditloggingsystem.audit.Auditable;
import com.codeplay.auditloggingsystem.entity.Employee;
import com.codeplay.auditloggingsystem.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    // CREATE Employee
    public Employee create(Employee emp) {
        return repo.save(emp);
    }

    // UPDATE Employee → auditable
    @Transactional
    @Auditable(action = "UPDATE", entity = "EMPLOYEE")
    public void update(Long id, Employee emp) {
        Employee existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Employee Not Found"));
        existing.setName(emp.getName());
        existing.setDepartment(emp.getDepartment());
        repo.save(existing);
    }

    // DELETE Employee → auditable
    @Transactional
    @Auditable(action = "DELETE", entity = "EMPLOYEE")
    public void delete(Long id) {
        Employee existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Employee Not Found"));
        repo.delete(existing);
    }

    // GET Employee (no audit)
    public Employee getEmployeeById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Employee Not Found"));
    }

    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }
}