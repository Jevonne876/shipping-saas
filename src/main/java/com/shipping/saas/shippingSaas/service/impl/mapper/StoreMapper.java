package com.shipping.saas.shippingSaas.service.impl.mapper;

import com.shipping.saas.shippingSaas.domain.Store;
import com.shipping.saas.shippingSaas.domain.dto.StoreDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {
    AddressesMapper.class,
    PhoneNumberMapper.class
})
public interface StoreMapper {

    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    Store toEntity(StoreDTO storeDTO);

    StoreDTO toDto(Store store);
}
