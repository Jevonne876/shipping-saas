package com.shipping.saas.shippingSaas.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    String code ;
    String message;
    Integer status;
}
