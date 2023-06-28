package com.example.gofood.Model;

import java.util.Date;

public class Orders {
    private int id_order, id_user,id_detail_order;
    private String create_at;

    public Orders(int id_order, int id_user, int id_detail_order, String create_at) {
        this.id_order=id_order;
        this.id_user=id_user;
        this.id_detail_order = id_detail_order;
        this.create_at = create_at;
    }

    public int getIDOrder() {
        return id_order;
    }
    public int getIDUser() {
        return id_user;
    }

    public int getIDDetailOrder() {
        return id_detail_order;
    }

    public String getCreateAt() {
        return create_at;
    }

}

