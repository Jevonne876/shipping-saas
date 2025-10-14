package com.shipping.saas.shippingSaas.domain.dto;

import com.shipping.saas.shippingSaas.domain.enums.AddressableType;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressesDTO {

    private UUID id;
    private UUID addressableId;

    private AddressableType addressableType;
    private String streetAddress;
    private String stateOrParish;
    private String postalCode;
    private String city;
    private String country;
    private Boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
}
