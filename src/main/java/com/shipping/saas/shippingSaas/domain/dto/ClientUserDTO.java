package com.shipping.saas.shippingSaas.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClientUserDTO {

    private String clientName;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String passwordHash;

    private String userType;

    private String role;

}
