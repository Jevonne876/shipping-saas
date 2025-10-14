package com.shipping.saas.shippingSaas.service.mapper;

import com.shipping.saas.shippingSaas.domain.clients.ClientSubscriptions;
import com.shipping.saas.shippingSaas.domain.dto.ClientSubscriptionsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "spring")
public interface ClientSubscriptionsMapper {


    // 🔄 Entity → DTO
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "subscriptionPlan.id", target = "subscriptionPlanId")
    @Mapping(source = "subscriptionPlan.name", target = "subscriptionPlanName")
    ClientSubscriptionsDTO toDto(ClientSubscriptions entity);

    // 🔁 DTO → Entity
    @Mapping(target = "client", ignore = true)              // handled separately in service
    @Mapping(target = "subscriptionPlan", ignore = true)
    // set manually or via lookup
    @Mapping(target = "isActive", source = "isActive")
    ClientSubscriptions toEntity(ClientSubscriptionsDTO dto);
}
