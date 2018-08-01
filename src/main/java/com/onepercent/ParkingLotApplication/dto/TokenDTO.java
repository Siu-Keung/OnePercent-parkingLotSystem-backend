package com.onepercent.ParkingLotApplication.dto;

import java.io.Serializable;

public class TokenDTO implements Serializable {
    String token;
    String role;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
