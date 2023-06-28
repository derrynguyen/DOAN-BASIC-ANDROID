package com.example.gofood.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gofood.Adapter.ProductAdapter;
import com.example.gofood.Config.API;
import com.example.gofood.Config.ConnectAPI;
import com.example.gofood.Config.SharedPrefManager;
import com.example.gofood.MainActivity;
import com.example.gofood.Model.LoginResponse;
import com.example.gofood.Model.Product;
import com.example.gofood.Model.RegisterResponse;
import com.example.gofood.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    TextView fullname , name  ;

    Button adCart;

    RecyclerView recyclerView;
    SharedPrefManager sharedPrefManager;

    Button search ;


    public HomeFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        sharedPrefManager = new SharedPrefManager(getActivity());

        recyclerView = view.findViewById(R.id.recyclerView);
        fullname = view.findViewById(R.id.txtFullname);

        fullname.setText(sharedPrefManager.getUser().getFullname());
        Log.d("TAG", "Trạng thái API trả về là: " + sharedPrefManager.getUser().getFullname());
        search = view.findViewById(R.id.search);
        name = view.findViewById(R.id.txtPhone);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadProducts(name.getText().toString());
            }
        });







        return view;
    }
    public void loadProducts(String name) {
        List<Product> productList = new ArrayList<>();

        Call<List<Product>> call = ConnectAPI.getInstance().getApi().search(name);

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    productList.clear();
                    productList.addAll(response.body());

                    //Log.d("onResponse", productList.get(0).getNameProduct());

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