package com.chat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginCommand
{
    private String username;
    private String password;

    LoginCommand(@JsonProperty("username") String username,
                 @JsonProperty("password") String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}