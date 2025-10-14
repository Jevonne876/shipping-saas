package com.shipping.saas.shippingSaas.domain.dto;

import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ClientDTO {
    private UUID id;
    private String name;
    private String contactPersonFirstName;
    private String contactPersonLastName;
    private String email;
    private String companyCode;
    private UUID subscriptionPlanId;   // ← must exist and be spelled exactly
    private String billingCycle;
    private String logoUrl;
    private Instant createdAt;
    private Instant updatedAt;
    private AddressesDTO address;
    private List<PhoneNumberDTO> phoneNumbers;
    private List<StoreDTO> stores;
    private WarehouseDTO warehouseAddress;
}


