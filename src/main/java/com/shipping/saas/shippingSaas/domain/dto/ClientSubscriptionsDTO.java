package com.shipping.saas.shippingSaas.domain.dto;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientSubscriptionsDTO {

    private UUID id;
    private UUID clientId;
    private UUID subscriptionPlanId;
    private String subscriptionPlanName; // or code, whichever you prefer
    private Boolean isActive;
    private String billingCycle;
    private String notes;
    private Instant startDate;
    private Instant endDate;
    private Instant createdAt;
    private Instant updatedAt;

}
