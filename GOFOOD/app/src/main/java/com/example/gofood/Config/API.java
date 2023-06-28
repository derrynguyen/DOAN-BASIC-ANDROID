package com.example.gofood.Config;

import okhttp3.ResponseBody;

import com.example.gofood.Model.Cart;
import com.example.gofood.Model.LoginResponse;
import com.example.gofood.Model.Orders;
import com.example.gofood.Model.OrdersDetail;
import com.example.gofood.Model.Product;
import com.example.gofood.Model.RegisterResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {

    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterResponse> register(
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("fullname") String fullname,
            @Field("addreas") String addreas

    );


    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(
            @Field("phone") String phone,
            @Field("password") String password
    );


    @GET("product.php")
    Call<List<Product>> getProductList();


    @FormUrlEncoded
    @POST("addcart.php")
    Call<Product> Addcart(
            @Field("id_user") int id_user,
            @Field("name_product") String name_product,
            @Field("price_product") int price_product,
            @Field("imgur") String imgur
    );


    @GET("cart.php")
    Call<List<Cart>> getCartList();

    @FormUrlEncoded
    @POST("deletecart.php")
    Call<ResponseBody> deleteCartItem(
            @Field("id_cart") int id_cart
    );


    @FormUrlEncoded
    @POST("payment.php")
    Call<ResponseBody> addPayment(@Field("cartList") String cartList);

    @FormUrlEncoded
    @POST("search.php")
    Call<List<Product>> search(@Field("name") String name);

    @GET("orders.php")
    Call<List<Orders>> getOrdersList();


    @GET("detailcart.php")
    Call<List<OrdersDetail>> getOrdersDetailList();

}
