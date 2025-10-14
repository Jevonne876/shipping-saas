package com.shipping.saas.shippingSaas.service.mapper;

import com.shipping.saas.shippingSaas.domain.Addresses;
import com.shipping.saas.shippingSaas.domain.dto.AddressesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressesMapper {

    AddressesMapper INSTANCE = Mappers.getMapper(AddressesMapper.class);


    AddressesDTO toDto(Addresses address);

    @Mapping(target = "isActive", source = "isActive")
    Addresses toEntity(AddressesDTO dto);
}
