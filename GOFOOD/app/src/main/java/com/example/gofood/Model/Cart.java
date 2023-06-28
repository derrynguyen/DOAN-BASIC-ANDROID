package com.example.gofood.Model;

public class Cart {
    private int id_cart, price_product,amount, id_user;
    private String name_product,imgur;

    public Cart(int id_cart,int id_user, String name_product, int price_product, String imgur,int amount) {
        this.id_cart=id_cart;
        this.id_user=id_user;
        this.name_product = name_product;
        this.price_product = price_product;
        this.imgur=imgur;
        this.amount=amount;

    }

    public int getId() {
        return id_cart;
    }
    public int getIDUser() {
        return id_user;
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
    public int getAmount() {
        return amount;
    }

}

