package com.learningmat.ecommerce.dto.request;

public record AuthenticationRequest(
    String username,
    String password) {
}
