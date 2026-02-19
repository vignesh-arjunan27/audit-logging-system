/*
 😉Author      : Vignesh Arjunan
 💻Role        : Software Engineer
 🐦‍🔥Created On  : 20-02-2026 00:25
 */

package com.codeplay.auditloggingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class AuditApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuditApplication.class, args);
    }
}