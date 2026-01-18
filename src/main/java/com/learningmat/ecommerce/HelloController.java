package com.learningmat.ecommerce;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HelloController {

    @GetMapping("/hello")
    public String xinChao() {
        return "Kết nối Database thành công!";
    }
}
