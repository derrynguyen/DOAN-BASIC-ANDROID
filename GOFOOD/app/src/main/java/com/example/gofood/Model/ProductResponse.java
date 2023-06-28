package com.example.gofood.Model;

import java.util.List;

public class ProductResponse {
    private boolean success;
    private String message;
    private List<Product> products;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Product> getProducts() {
        return products;
    }
}
