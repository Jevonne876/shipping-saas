package com.shipping.saas.shippingSaas.service.impl.mapper;

import com.shipping.saas.shippingSaas.domain.WarehouseAddress;
import com.shipping.saas.shippingSaas.domain.dto.WarehouseAddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {
    AddressesMapper.class,
    PhoneNumberMapper.class
})
public interface WarehouseAddressMapper {

    WarehouseAddressMapper INSTANCE = Mappers.getMapper(WarehouseAddressMapper.class);

    WarehouseAddressDTO toDto(WarehouseAddress entity);

    WarehouseAddress toEntity(WarehouseAddressDTO dto);
}
