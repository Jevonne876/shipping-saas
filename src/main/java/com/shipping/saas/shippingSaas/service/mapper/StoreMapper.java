package com.shipping.saas.shippingSaas.service.mapper;

import com.shipping.saas.shippingSaas.domain.Store;
import com.shipping.saas.shippingSaas.domain.dto.StoreDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {
    AddressesMapper.class,
    PhoneNumberMapper.class
})
public interface StoreMapper {

    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    Store toEntity(StoreDTO storeDTO);

    @InheritInverseConfiguration
    @Mapping(target = "client", ignore = true)
    StoreDTO toDto(Store store);
}
