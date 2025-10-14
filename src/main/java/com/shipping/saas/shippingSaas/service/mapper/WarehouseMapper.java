package com.shipping.saas.shippingSaas.service.mapper;

import com.shipping.saas.shippingSaas.domain.Warehouse;
import com.shipping.saas.shippingSaas.domain.dto.WarehouseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {
    AddressesMapper.class,
    PhoneNumberMapper.class
})
public interface WarehouseMapper {

    WarehouseMapper INSTANCE = Mappers.getMapper(WarehouseMapper.class);

    @Mapping(target = "client", ignore = true)
    WarehouseDTO toDto(Warehouse entity);

    Warehouse toEntity(WarehouseDTO dto);
}
