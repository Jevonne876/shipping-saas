package com.shipping.saas.shippingSaas.service.impl;

import com.shipping.saas.shippingSaas.domain.Addresses;
import com.shipping.saas.shippingSaas.repository.AddressesRepository;
import com.shipping.saas.shippingSaas.service.AddressesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressesServiceImpl implements AddressesService {

    private final AddressesRepository addressesRepository;

    @Override
    public Addresses createAddresses(Addresses addresses) {

        log.info("creating new address for owner {} address type {}", addresses.getAddressableId(), addresses.getAddressableType());

        Addresses newAddresses = Addresses.builder()
            .addressableId(addresses.getAddressableId())
            .addressableType(addresses.getAddressableType())
            .streetAddress(addresses.getStreetAddress())
            .stateOrParish(addresses.getStateOrParish())
            .city(addresses.getCity())
            .postalCode(addresses.getPostalCode())
            .country(addresses.getCountry())
            .build();

        return addressesRepository.save(newAddresses);
    }

    @Override
    public Addresses updateAddresses(Addresses addresses) {

        log.info("updating address for owner {}", addresses.getAddressableId());

        Addresses updatedAddresses = addressesRepository.findById(addresses.getAddressableId())
            .orElseThrow(() -> new IllegalArgumentException("address id not found " + addresses.getAddressableId()));

        updatedAddresses.setStreetAddress(addresses.getStreetAddress());
        updatedAddresses.setAddressableType(addresses.getAddressableType());
        updatedAddresses.setStateOrParish(addresses.getStateOrParish());
        updatedAddresses.setCity(addresses.getCity());
        updatedAddresses.setPostalCode(addresses.getPostalCode());
        updatedAddresses.setCountry(addresses.getCountry());


        return addressesRepository.save(updatedAddresses);
    }

    @Override
    public Optional<Addresses> findById(UUID addressesId) {

        log.info("finding address by id {}", addressesId);

        Addresses addresses = addressesRepository.findById(addressesId).orElseThrow(() -> new IllegalArgumentException("address id not found " + addressesId));

        return Optional.of(addresses);
    }

    @Override
    public List<Addresses> findAddressesByOwnerId(UUID ownerId) {

        log.info("finding addresses for owner {}", ownerId);

        return addressesRepository.findAddressesByOwnerId(ownerId);
    }

    @Override
    public void deleteAddresses(UUID id) {

        log.info("deleting address for owner {}", id);

        addressesRepository.deleteById(id);

    }


}
