package com.shipping.saas.shippingSaas.service.mapper;

import com.shipping.saas.shippingSaas.domain.PhoneNumbers;
import com.shipping.saas.shippingSaas.domain.dto.PhoneNumberDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PhoneNumberMapper {


    PhoneNumberDTO toDto(PhoneNumbers phoneNumber);

    @Mapping(target = "isActive", source = "isActive")
    PhoneNumbers toEntity(PhoneNumberDTO phoneNumberDTO);

}
