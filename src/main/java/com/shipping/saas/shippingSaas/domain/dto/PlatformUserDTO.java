package com.shipping.saas.shippingSaas.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlatformUserDTO {


    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String passwordHash;
    private String role;
    private String userType;
    private String streetAddress;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
