package com.shipping.saas.shippingSaas.service.mapper;

import com.shipping.saas.shippingSaas.domain.clients.Client;
import com.shipping.saas.shippingSaas.domain.clients.ClientSubscriptions;
import com.shipping.saas.shippingSaas.domain.dto.ClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
    AddressesMapper.class,
    PhoneNumberMapper.class,
    StoreMapper.class,
    WarehouseMapper.class
})
public interface ClientMapper {




    ClientDTO toDto(Client client);

    // DTO → Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subscriptions", ignore = true)
    // handled elsewhere
    // id is usually generated
    Client toEntity(ClientDTO dto);

    // Custom method to extract the active plan name
    default String getActiveSubscriptionName(Client client) {
        if (client.getSubscriptions() == null) return null;
        return client.getSubscriptions().stream()
            .filter(ClientSubscriptions::isActive)
            .findFirst()
            .map(cs -> cs.getSubscriptionPlan().getName())
            .orElse(null);
    }
}
