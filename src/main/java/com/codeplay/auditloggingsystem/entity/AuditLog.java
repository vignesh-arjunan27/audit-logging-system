package com.codeplay.auditloggingsystem.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;      // Who performed the action
    private String action;        // CREATE / UPDATE / DELETE
    private String entityName;    // Example: EMPLOYEE
    private Long entityId;        // ID of the affected entity

    @Column(columnDefinition = "TEXT")
    private String oldValue;      // Before change

    @Column(columnDefinition = "TEXT")
    private String newValue;      // After change

    private LocalDateTime timestamp;  // When change happened
}