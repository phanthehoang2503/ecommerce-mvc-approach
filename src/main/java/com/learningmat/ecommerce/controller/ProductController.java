package com.learningmat.ecommerce.controller;

import com.learningmat.ecommerce.dto.ApiResponse;
import com.learningmat.ecommerce.dto.request.ProductRequest;
import com.learningmat.ecommerce.model.Product;
import com.learningmat.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        Product prod = productService.createProduct(productRequest);
        return ApiResponse.<Product>builder()
                .result(prod)
                .message("Created new product")
                .build();
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<Product>> getProduct() {
        return ApiResponse.<List<Product>>builder()
                .result(productService.getProduct())
                .message("List of product")
                .build();
    }

    @PutMapping("/{productId}")
    public ApiResponse<Product> updateProduct(@PathVariable int productId,
                                              @RequestBody @Valid ProductRequest productRequest) {
        return ApiResponse.<Product>builder()
                .result(productService.updateProduct(productId, productRequest))
                .message("Updated successful")
                .build();
    }

    @DeleteMapping("/{productId}")
    public ApiResponse<String> deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
        return ApiResponse.<String>builder()
                .result("Product has been deleted")
                .message("Product delete successfully")
                .build();
    }
}
