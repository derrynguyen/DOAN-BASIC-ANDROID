package com.example.gofood.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gofood.Config.SharedPrefManager;
import com.example.gofood.R;

public class ProfileFragment extends Fragment {

    Button logout;
    TextView fullname , phone, addreas;
    SharedPrefManager sharedPrefManager;

    public ProfileFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        sharedPrefManager=new SharedPrefManager(getActivity());

        logout = view.findViewById(R.id.logout);

        fullname=view.findViewById(R.id.txtFullName);
        phone=view.findViewById(R.id.txtPhone);
        addreas=view.findViewById(R.id.txtAddreas);

        fullname.setText(sharedPrefManager.getUser().getFullname());
        phone.setText(sharedPrefManager.getUser().getPhone());
        addreas.setText(sharedPrefManager.getUser().getAddreas());

        Log.d("TAG", "Địa chỉ: " +sharedPrefManager.getUser().getAddreas());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        return view;

    }
    private void logoutUser() {
        sharedPrefManager.logout();
        Intent intent=new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}