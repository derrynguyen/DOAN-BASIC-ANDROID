package com.example.gofood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gofood.Activity.HomeActivity;
import com.example.gofood.Activity.LoginActivity;
import com.example.gofood.Config.ConnectAPI;
import com.example.gofood.Model.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.gofood.Config.SharedPrefManager;

public class MainActivity extends AppCompatActivity {

    EditText phoneNumber, passwords, fullName,Addreas;
    Button register;
    TextView gotoLogin;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        phoneNumber = findViewById(R.id.txtPhone);
        passwords = findViewById(R.id.txtPassowrd);
        fullName = findViewById(R.id.txtFullname);
        Addreas = findViewById(R.id.txtAddreas);

        gotoLogin = findViewById(R.id.gotoLogin);

        register = findViewById(R.id.btnRes);

        sharedPrefManager=new SharedPrefManager(getApplicationContext());

        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = phoneNumber.getText().toString();
                String password = passwords.getText().toString();
                String fullname = fullName.getText().toString();
                String addreas = Addreas.getText().toString();


                if (phone.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phone.length() != 10) {
                    Toast.makeText(getApplicationContext(), "Số điện thoại phải đủ 10 số", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (fullname.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ họ tên", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (addreas.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ địa chỉ", Toast.LENGTH_SHORT).show();
                    return;
                }
                Call<RegisterResponse> call = ConnectAPI.getInstance().getApi().register(phone, password,fullname, addreas);

                call.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        RegisterResponse registerResponse = response.body();
                        if (response.code() == 200 ) {

                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);

                            Toast.makeText(MainActivity.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (response.code() == 403) {
                            Toast.makeText(MainActivity.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }

}