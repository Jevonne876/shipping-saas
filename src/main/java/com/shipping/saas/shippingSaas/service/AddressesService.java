package com.shipping.saas.shippingSaas.service;

import com.shipping.saas.shippingSaas.domain.Addresses;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressesService {

    Addresses createAddresses(Addresses addresses);

    Addresses updateAddresses(Addresses addresses);

    List<Addresses> findAddressesByOwnerId(UUID ownerId);

    Optional<Addresses> findById(UUID addressesId);

    void deleteAddresses(UUID id);





}
