package com.example.gofood.Model;

public class RegisterResponse {

    String error;
    String success;

    public RegisterResponse(String error, String success) {
        this.error = error;
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return success;
    }

    public void setMessage(String success) {
        this.success = success;
    }
}
