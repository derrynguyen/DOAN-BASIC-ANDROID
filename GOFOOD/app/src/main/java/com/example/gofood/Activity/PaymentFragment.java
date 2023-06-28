package com.example.gofood.Activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gofood.Adapter.OrderAdapter;
import com.example.gofood.Config.ConnectAPI;
import com.example.gofood.Config.SharedPrefManager;
import com.example.gofood.Model.Orders;
import com.example.gofood.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PaymentFragment extends Fragment {

    private RecyclerView recyclerViewCart;
    private OrderAdapter orderAdapter;
    private List<Orders> ordersList;

    SharedPrefManager sharedPrefManager;
    public PaymentFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment, container, false);
        ordersList = new ArrayList<>();


        recyclerViewCart = view.findViewById(R.id.recyclerViewPayment);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(getContext()));
        orderAdapter = new OrderAdapter(ordersList);


        recyclerViewCart.setAdapter(orderAdapter);

        loadPayment();

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadPayment();
    }

    public void loadPayment() {

        sharedPrefManager = new SharedPrefManager(getContext());

        int id_user = sharedPrefManager.getUser().getId(); // Lấy id_user từ SharedPrefManager

        Call<List<Orders>> call = ConnectAPI.getInstance().getApi().getOrdersList();

        call.enqueue(new Callback<List<Orders>>() {
            @Override
            public void onResponse(Call<List<Orders>> call, Response<List<Orders>> response) {
                if (response.isSuccessful()) {
                    ordersList = response.body();
                    List<Orders> userOrdersList = new ArrayList<>();

                    for (Orders orders : ordersList) {
                        if (orders.getIDUser() == id_user) {
                            userOrdersList.add(orders);
                        }
                    }
                    orderAdapter.setData(userOrdersList);
                    orderAdapter.notifyDataSetChanged();

                } else {
                    Log.e("TAG", "Lỗi khi lấy dữ liệu giỏ hàng từ API: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Orders>> call, Throwable t) {
                Log.e("TAG", "Lỗi khi kết nối đến API: " + t.getMessage());
            }
        });
    }
}