package com.shipping.saas.shippingSaas.service.impl.mapper;

import com.shipping.saas.shippingSaas.domain.PhoneNumbers;
import com.shipping.saas.shippingSaas.domain.dto.PhoneNumberDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "string")
public interface PhoneNumberMapper {

    PhoneNumberMapper INSTANCE = Mappers.getMapper(PhoneNumberMapper.class);

    PhoneNumberDTO toDto(PhoneNumbers phoneNumber);

    PhoneNumbers toEntity(PhoneNumberDTO phoneNumberDTO);

}
