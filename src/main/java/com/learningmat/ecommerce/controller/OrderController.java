package com.learningmat.ecommerce.controller;

import com.learningmat.ecommerce.dto.response.ApiResponse;
import com.learningmat.ecommerce.model.Order;
import com.learningmat.ecommerce.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
@Tag(name = "Order controller", description = "Obviously for ordering item and check order history")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/checkout")
    public ApiResponse<Order> checkout(@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getSubject();
        return ApiResponse.<Order>builder()
                .result(orderService.placeOrder(username))
                .message("Place order successfully!!!")
                .build();
    }

    @GetMapping("/my-orders")
    public ApiResponse<List<Order>> orderHistory(@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getSubject();

        List<Order> orders = orderService.getOrders(username);
        return ApiResponse.<List<Order>>builder()
                .result(orders)
                .build();
    }
}
