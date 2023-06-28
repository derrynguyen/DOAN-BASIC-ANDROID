package com.example.gofood.Config;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.gofood.Model.User;

import java.util.Map;

public class SharedPrefManager {

    private static String SHARED_PREF_NAME="gofood";
    private SharedPreferences sharedPreferences;
    Context context;

    private SharedPreferences.Editor editor;


    public SharedPrefManager(Context context) {
        this.context = context;
    }

    public void saveUser(User user) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt("id", user.getId());
        editor.putString("phone", user.getPhone());
        editor.putString("fullname", user.getFullname());
        editor.putString("addreas", user.getAddreas());
        editor.putBoolean("logged", true);
        editor.apply();
    }



    public boolean isLoggedIn(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("logged",false);
    }

    public User getUser(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new User(sharedPreferences.getInt("id",-1),
                sharedPreferences.getString("phone",null),
                sharedPreferences.getString("fullname",null),
                sharedPreferences.getString("addreas",null));
    }

    public void logout(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();

    }


}
