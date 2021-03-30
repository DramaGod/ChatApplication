package com.example.demo.token;

import javax.validation.constraints.NotNull;

public class CreateTokenRequest {

    @NotNull
    protected String email;

    @NotNull
    protected String password;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
