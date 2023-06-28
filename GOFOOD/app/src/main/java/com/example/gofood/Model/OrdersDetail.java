package com.example.gofood.Model;

public class OrdersDetail {
    private int id_payment, id_order,id_user, price_product,amount,total;
    private String name_product,imgur;

    public OrdersDetail(int id_payment, int id_order, String name_product, int id_user, String imgur, int amount,int total, int price_product) {
        this.id_payment=id_payment;
        this.id_user=id_user;
        this.id_order=id_order;
        this.name_product = name_product;
        this.price_product = price_product;
        this.imgur=imgur;
        this.amount=amount;
        this.total=total;

    }

    public int getIdPayment() {
        return id_payment;
    }


    public int getIdUser() {
        return id_user;
    }
    public int getIdOrders() {
        return id_order;
    }
    public String getIdNameProduct() {
        return name_product;
    }
    public int getIdPriceProduct() {
        return price_product;
    }
    public String getImgur() {
        return imgur;
    }
    public int getAmount() {
        return amount;
    }
    public int getTotal() {
        return total;
    }

}

