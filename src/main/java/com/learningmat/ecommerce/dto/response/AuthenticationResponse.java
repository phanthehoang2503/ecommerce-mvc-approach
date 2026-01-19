package com.learningmat.ecommerce.dto.response;


import lombok.Builder;

@Builder
public record AuthenticationResponse(
    String token,
    boolean authenticated // check if login successful
) {
}
