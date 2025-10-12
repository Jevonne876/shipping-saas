package com.shipping.saas.shippingSaas.domain.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class StoreDTO {

    private UUID id;
    private ClientDTO client;
    private String name;
    private String code;
    private Boolean isActive;
    private AddressesDTO addresses;
    private List<PhoneNumberDTO> phoneNumbers;
    private Instant createdAt;
    private Instant updatedAt;


}
