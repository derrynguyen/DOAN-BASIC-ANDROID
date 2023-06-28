package com.example.gofood.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gofood.Config.ConnectAPI;
import com.example.gofood.Config.SharedPrefManager;
import com.example.gofood.MainActivity;
import com.example.gofood.Model.LoginResponse;
import com.example.gofood.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText phoneNumber,passwords;
    TextView gotoRes;
    Button login;
    private int idUser;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phoneNumber = findViewById(R.id.txtPhone);
        passwords = findViewById(R.id.txtPassowrd);
        login= findViewById(R.id.btnLogin);

        gotoRes = findViewById(R.id.gotoRes);

        sharedPrefManager=new SharedPrefManager(getApplicationContext());

        HomeActivity homeActivity = new HomeActivity();
        gotoRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = phoneNumber.getText().toString();
                String password = passwords.getText().toString();

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

                Call<LoginResponse> call= ConnectAPI.getInstance().getApi().login(phone,password);

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                        LoginResponse loginResponse=response.body();
                        Log.d("TAG", "ID user : " +  response.code());

                        if(loginResponse.getError().equals("200")){
                            sharedPrefManager.saveUser(loginResponse.getUser());

                            Intent intent=new Intent(LoginActivity.this,HomeActivity.class);

                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                        }
                        else if(loginResponse.getError().equals("400")){
                            Toast.makeText(LoginActivity.this,"Kiểm tra lại số điện thoại hoặc mật khẩu", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                        Toast.makeText(LoginActivity.this,"Lỗi kết nối", Toast.LENGTH_SHORT).show();
                    }
                });

            }

        });

    }
    @Override
    protected void onStart() {
        super.onStart();

        if(sharedPrefManager.isLoggedIn()){
            Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}