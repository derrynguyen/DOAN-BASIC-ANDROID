package com.example.gofood.Activity;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.gofood.Adapter.CartAdapter;
import com.example.gofood.Adapter.ProductAdapter;
import com.example.gofood.Config.ConnectAPI;
import com.example.gofood.Model.Cart;
import com.example.gofood.Model.Product;
import com.example.gofood.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    CartFragment cartFragment = new CartFragment();

    PaymentFragment paymentFragment = new PaymentFragment();

    ProfileFragment profileFragment = new ProfileFragment();

    RecyclerView recyclerView;
    CartAdapter cartAdapter; // Khai báo Adapter của CartFragment

    private MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Điều hướng
        bottomNavigationView = findViewById(R.id.BottomNavigationView);
        FrameLayout layoutFragment = findViewById(R.id.layout_fragment);

        getSupportFragmentManager().beginTransaction().replace(R.id.layout_fragment, homeFragment).commit();

        homeFragment.getViewLifecycleOwnerLiveData().observe(this, viewLifecycleOwner -> {
            if (viewLifecycleOwner != null) {
                View fragmentView = homeFragment.requireView();
                recyclerView = fragmentView.findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(fragmentView.getContext()));

                loadProducts();

                bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_buy:
                                getSupportFragmentManager().beginTransaction().replace(R.id.layout_fragment, homeFragment).commit();
                                break;
                            case R.id.action_cart:
                                getSupportFragmentManager().beginTransaction().replace(R.id.layout_fragment, cartFragment).commit();
                                break;

                            case R.id.action_payment:
                                getSupportFragmentManager().beginTransaction().replace(R.id.layout_fragment, paymentFragment).commit();
                                break;
                            case R.id.action_profile:
                                getSupportFragmentManager().beginTransaction().replace(R.id.layout_fragment, profileFragment).commit();
                                break;
                        }
                        return true;
                    }
                });
            }
        });
    }

    public void loadProducts() {
        List<Product> productList = new ArrayList<>();

        Call<List<Product>> call = ConnectAPI.getInstance().getApi().getProductList();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    productList.clear();
                    productList.addAll(response.body());

                    ProductAdapter productAdapter = new ProductAdapter(productList);
                    recyclerView.setAdapter(productAdapter);
                } else {
                    Log.e("TAG", "Lỗi khi lấy dữ liệu sản phẩm từ API: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("TAG", "Lỗi khi kết nối đến API: " + t.getMessage());
            }
        });
    }


}
