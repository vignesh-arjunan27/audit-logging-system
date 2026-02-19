/*
 😉Author      : Vignesh Arjunan
 💻Role        : Software Engineer
 🐦‍🔥Created On  : 20-02-2026 00:24
 */

package com.codeplay.auditloggingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeplay.auditloggingsystem.entity.AuditLog;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}