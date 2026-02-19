/*
 😉Author      : Vignesh Arjunan
 💻Role        : Software Engineer
 🐦‍🔥Created On  : 20-02-2026 00:23
 */

package com.codeplay.auditloggingsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String department;
}