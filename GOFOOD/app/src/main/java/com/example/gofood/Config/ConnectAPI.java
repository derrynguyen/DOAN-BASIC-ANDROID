package com.example.gofood.Config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.logging.HttpLoggingInterceptor;





import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ConnectAPI {
    private static String BASE_URL="http://172.17.21.36/api/";
    private static ConnectAPI ConnectAPI;
    private static Retrofit retrofit;

    private OkHttpClient.Builder builder = new OkHttpClient.Builder();

    private HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();


    public ConnectAPI() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();


    }


    public static synchronized ConnectAPI getInstance(){

        if(ConnectAPI==null){
            ConnectAPI=new ConnectAPI();
        }
        return ConnectAPI;
    }

    public API getApi(){
        return retrofit.create(API.class);
    }
}
