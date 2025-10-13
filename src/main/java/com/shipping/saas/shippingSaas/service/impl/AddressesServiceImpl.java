package com.shipping.saas.shippingSaas.service.impl;

import com.shipping.saas.shippingSaas.domain.Addresses;
import com.shipping.saas.shippingSaas.domain.dto.AddressesDTO;
import com.shipping.saas.shippingSaas.repository.AddressesRepository;
import com.shipping.saas.shippingSaas.service.AddressesService;
import com.shipping.saas.shippingSaas.service.mapper.AddressesMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AddressesServiceImpl implements AddressesService {

    private final AddressesRepository addressesRepository;
    private final AddressesMapper addressesMapper;

    @Override
    public AddressesDTO createAddresses(AddressesDTO addresses) {

        log.info("creating new address for owner {} address type {}", addresses.getAddressableId(), addresses.getAddressableType());

        Addresses addressesEntity = addressesMapper.toEntity(addresses);

        return addressesMapper.toDto(addressesRepository.save(addressesEntity));
    }

    @Override
    public AddressesDTO updateAddresses(AddressesDTO addresses) {

        log.info("updating address for owner {}", addresses.getAddressableId());

        Addresses updatedAddresses = addressesRepository.findById(addresses.getAddressableId())
            .orElseThrow(() -> new IllegalArgumentException("address id not found " + addresses.getAddressableId()));

        updatedAddresses.setStreetAddress(addresses.getStreetAddress());
        updatedAddresses.setAddressableType(addresses.getAddressableType());
        updatedAddresses.setStateOrParish(addresses.getStateOrParish());
        updatedAddresses.setCity(addresses.getCity());
        updatedAddresses.setPostalCode(addresses.getPostalCode());
        updatedAddresses.setCountry(addresses.getCountry());

        return addressesMapper.toDto(addressesRepository.save(updatedAddresses));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AddressesDTO> findById(UUID addressesId) {

        log.info("finding address by id {}", addressesId);

        return Optional.of(
            addressesMapper
                .toDto(addressesRepository
                    .findById(addressesId)
                    .orElseThrow(() -> new IllegalArgumentException("address id not found " + addressesId))));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressesDTO> findAddressesByAddressableId(UUID ownerId) {

        log.info("finding addresses for owner {}", ownerId);

        return addressesRepository
            .findAddressesByAddressableId(ownerId)
            .stream()
            .map(addressesMapper::toDto).toList();
    }

    @Override
    public void deleteAddresses(UUID id) {

        log.info("deleting address for owner {}", id);

        addressesRepository.deleteById(id);

    }


}
