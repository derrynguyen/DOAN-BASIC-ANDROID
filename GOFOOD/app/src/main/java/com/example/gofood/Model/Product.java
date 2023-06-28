package com.example.gofood.Model;

public class Product {
    private int id, price_product;
    private String name_product,imgur;

    public Product(int id, String name_product,int price_product,String imgur) {
        this.id=id;
        this.name_product = name_product;
        this.price_product = price_product;
        this.imgur=imgur;
    }

    public int getId() {
        return id;
    }

    public String getNameProduct() {
        return name_product;
    }

    public int getPriceProduct() {
        return price_product;
    }
    public String getImgur() {
        return imgur;
    }

}

