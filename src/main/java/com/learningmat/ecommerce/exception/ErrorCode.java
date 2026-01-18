package com.learningmat.ecommerce.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "not defined error yet"),
    INVALID_KEY(1001, "Wrong message key"),

    INVALID_NAME(1002, "Product's name should contain at least 3 character"),
    INVALID_PRICE(1004, "Price shouldn't be negative"),
    INVALID_PASSWORD(1005, "Password should contain 6 character"),
    PRODUCT_NOT_FOUND(1006, "Product not found (fr tho)"),

    USER_EXISTED(1007, "Username already exist..."),
    USER_NOTFOUND(1008, "User not found...")
    ;

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
