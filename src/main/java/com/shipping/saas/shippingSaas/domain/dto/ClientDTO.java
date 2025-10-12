package com.shipping.saas.shippingSaas.domain.dto;

import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Table
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
    private String subscriptionPlan;
    private String logoUrl;
    private Instant createdAt;
    private Instant updatedAt;

    private AddressesDTO address;
    private List<PhoneNumberDTO> phoneNumbers;
    private List<StoreDTO> stores;
    private WarehouseAddressDTO warehouseAddress;

}
