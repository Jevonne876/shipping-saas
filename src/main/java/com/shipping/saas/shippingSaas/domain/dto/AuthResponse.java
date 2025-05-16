package com.shipping.saas.shippingSaas.domain.dto;

import lombok.Getter;

@Getter
public class AuthResponse {

    private String jwt;

    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

}
