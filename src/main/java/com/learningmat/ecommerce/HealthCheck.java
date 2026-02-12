package com.learningmat.ecommerce;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HealthCheck {

    @GetMapping("/health")
    public String health() {
        return "Application is running";
    }
}
