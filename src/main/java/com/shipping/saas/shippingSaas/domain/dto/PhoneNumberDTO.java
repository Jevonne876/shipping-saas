package com.shipping.saas.shippingSaas.domain.dto;

import com.shipping.saas.shippingSaas.domain.enums.OwnerType;
import com.shipping.saas.shippingSaas.domain.enums.PhoneType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneNumberDTO {

    private UUID id;
    private UUID ownerId;
    private OwnerType ownerType;
    private String phoneNumber;
    private PhoneType phoneType;
    private Boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
}
