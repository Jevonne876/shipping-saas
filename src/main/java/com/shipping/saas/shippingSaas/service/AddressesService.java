package com.shipping.saas.shippingSaas.service;

import com.shipping.saas.shippingSaas.domain.Addresses;
import com.shipping.saas.shippingSaas.domain.dto.AddressesDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressesService {

    AddressesDTO createAddresses(AddressesDTO addresses);

    AddressesDTO updateAddresses(AddressesDTO addresses);

    List<AddressesDTO> findAddressesByAddressableId(UUID ownerId);

    Optional<AddressesDTO> findById(UUID addressesId);

    void deleteAddresses(UUID id);





}
