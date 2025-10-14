package com.shipping.saas.shippingSaas.service.mapper;

import com.shipping.saas.shippingSaas.domain.WarehouseAddress;
import com.shipping.saas.shippingSaas.domain.dto.WarehouseAddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {
    AddressesMapper.class,
    PhoneNumberMapper.class
})
public interface WarehouseAddressMapper {

    WarehouseAddressMapper INSTANCE = Mappers.getMapper(WarehouseAddressMapper.class);

    @Mapping(target = "client", ignore = true)
    WarehouseAddressDTO toDto(WarehouseAddress entity);

    WarehouseAddress toEntity(WarehouseAddressDTO dto);
}
