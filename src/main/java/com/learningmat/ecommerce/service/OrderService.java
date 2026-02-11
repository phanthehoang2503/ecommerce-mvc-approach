package com.learningmat.ecommerce.service;

import com.learningmat.ecommerce.exception.AppException;
import com.learningmat.ecommerce.exception.ErrorCode;
import com.learningmat.ecommerce.model.Cart;
import com.learningmat.ecommerce.model.Order;
import com.learningmat.ecommerce.model.OrderItem;
import com.learningmat.ecommerce.model.User;
import com.learningmat.ecommerce.repository.CartRepository;
import com.learningmat.ecommerce.repository.OrderRepository;
import com.learningmat.ecommerce.repository.ProductRepository;
import com.learningmat.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartService cartService;

    @Transactional
    public Order placeOrder(String username) {
        // find user
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        // do they have the cart?
        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_CART_NOTFOUND));
        // if yes then create an order
        Order order = Order.builder()
                .user(user)
                .status("Pending")
                .orderDate(LocalDateTime.now())
                .orderItems(new ArrayList<>())
                .build();
        // take items from user's cart and then put it on the list of other items
        List<OrderItem> orderItems = cart.getItems().stream()
                .map(item -> OrderItem.builder()
                        .order(order)
                        .product(item.getProduct())
                        .quantity(item.getQuantity())
                        .price(item.getProduct().getPrice()).build())
                .toList();
        order.setOrderItems(orderItems);
        // look through the list and calculate the total
        Long total = orderItems.stream()
                .mapToLong(item -> (long) (item.getQuantity() * item.getPrice()))
                .sum();
        order.setTotalAmount(total);
        // Finally save an order
        Order savedOrder = orderRepository.save(order);

        // clear cart after placed an order
        cartService.clearCart(username);
        return savedOrder;
    }

    public List<Order> getOrders(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));

        return orderRepository.findByUserId(user.getId());
    }
}
