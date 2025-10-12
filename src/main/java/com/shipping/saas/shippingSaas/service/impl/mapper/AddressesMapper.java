package com.shipping.saas.shippingSaas.service.impl.mapper;

import com.shipping.saas.shippingSaas.domain.Addresses;
import com.shipping.saas.shippingSaas.domain.dto.AddressesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressesMapper {

    AddressesMapper INSTANCE = Mappers.getMapper(AddressesMapper.class);

    AddressesDTO toDto(Addresses address);

    Addresses toEntity(AddressesDTO dto);
}
