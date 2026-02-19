package com.codeplay.auditloggingsystem.audit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // Method level annotation
@Retention(RetentionPolicy.RUNTIME) // Runtime-le available irukkum
public @interface Auditable {
    String action(); // CREATE / UPDATE / DELETE

    String entity(); // Entity name, e.g., EMPLOYEE
}