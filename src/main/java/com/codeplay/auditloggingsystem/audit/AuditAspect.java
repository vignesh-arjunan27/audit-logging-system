/*
 😉Author      : Vignesh Arjunan
 💻Role        : Software Engineer
 🐦‍🔥Created On  : 20-02-2026 00:23
 */

package com.codeplay.auditloggingsystem.audit;

import java.time.LocalDateTime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codeplay.auditloggingsystem.entity.AuditLog;
import com.codeplay.auditloggingsystem.repository.AuditLogRepository;
import com.codeplay.auditloggingsystem.repository.EmployeeRepository;

@Aspect
@Component
public class AuditAspect {

    @Autowired
    private AuditLogRepository auditRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    @Around("@annotation(auditable)")
    public Object audit(ProceedingJoinPoint pjp, Auditable auditable) throws Throwable {

        // 1️) Capture entity ID (first argument assumed to be ID)
        Long entityId = (Long) pjp.getArgs()[0];

        // 2️) Fetch old value from DB
        String oldValue = employeeRepo.findById(entityId).map(Object::toString).orElse("NOT_FOUND");

        // 3️) Proceed with actual business method
        Object result = pjp.proceed();

        // 4️) Capture new value
        String newValue;
        if ("DELETE".equals(auditable.action()))
            newValue = "DELETED";
        else {
            newValue = employeeRepo.findById(entityId)
                    .map(Object::toString)
                    .orElse("NOT_FOUND");
        }

        // 5️) Collect audit log
        AuditLog log = new AuditLog();
        log.setUsername("SYSTEM_USER");
        log.setAction(auditable.action());
        log.setEntityName(auditable.entity());
        log.setEntityId(entityId);
        log.setOldValue(oldValue);
        log.setNewValue(newValue);
        log.setTimestamp(LocalDateTime.now());

        // 6️) Save audit log
        try {
            auditRepo.save(log);
        } catch (Exception e) {
            System.out.println("Audit failed but business continued");
        }

        return result;
    }
}