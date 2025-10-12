package com.shipping.saas.shippingSaas.domain.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionsPlanDTO {
    private UUID id;
    private String code;
    private String name;
    private String description;
    private Double monthlyPrice;
    private Double annualPrice;
    private Boolean isActive;

}
