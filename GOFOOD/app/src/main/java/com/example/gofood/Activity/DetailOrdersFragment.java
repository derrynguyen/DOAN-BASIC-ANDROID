package com.example.gofood.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gofood.Adapter.OrderAdapter;
import com.example.gofood.Model.Orders;
import com.example.gofood.R;

public class DetailOrdersFragment extends Fragment {

    public DetailOrdersFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_orders, container, false);


        if (getArguments() != null) {
            int orderId = getArguments().getInt("id_order");
        }





        return view;
    }


}