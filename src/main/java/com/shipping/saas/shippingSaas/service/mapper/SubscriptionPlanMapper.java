package com.shipping.saas.shippingSaas.service.mapper;

import com.shipping.saas.shippingSaas.domain.clients.SubscriptionPlans;
import com.shipping.saas.shippingSaas.domain.dto.SubscriptionsPlanDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionPlanMapper {

    SubscriptionsPlanDTO toDto(SubscriptionPlans entity);
    SubscriptionPlans toEntity(SubscriptionsPlanDTO dto);
}
