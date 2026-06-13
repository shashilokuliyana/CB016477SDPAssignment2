package com.sdp.logistics.dto;

public class AuthResponse {

    private Long customerId;
    private String name;
    private String email;
    private String phone;
    private String message;

    public AuthResponse(Long customerId, String name, String email, String phone, String message) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.message = message;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getMessage() {
        return message;
    }
}

