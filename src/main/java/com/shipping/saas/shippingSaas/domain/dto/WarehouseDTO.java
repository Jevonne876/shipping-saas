package com.shipping.saas.shippingSaas.domain.dto;

import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseDTO {

    private UUID id;
    private ClientDTO client;
    private String name;
    private String code;
    private AddressesDTO address;
    private String suitePrefix;
    private List<PhoneNumberDTO> phoneNumbers;
    private Instant createdAt;
    private Instant updatedAt;

}
