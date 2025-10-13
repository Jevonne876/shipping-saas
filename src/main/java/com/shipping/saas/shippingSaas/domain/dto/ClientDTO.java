package com.shipping.saas.shippingSaas.domain.dto;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDTO {
    private String id;
    private String name;
    private String contactPersonFirstName;
    private String contactPersonLastName;
    private String email;
    private String companyCode;
    private String subscriptionPlan;   // ← must exist and be spelled exactly
    private String logoUrl;
    private Instant createdAt;
    private Instant updatedAt;
    private AddressesDTO address;
    private List<PhoneNumberDTO> phoneNumbers;
    private List<StoreDTO> stores;
    private WarehouseAddressDTO warehouseAddress;
}


