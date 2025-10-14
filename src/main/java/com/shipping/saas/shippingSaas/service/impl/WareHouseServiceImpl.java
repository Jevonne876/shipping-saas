package com.shipping.saas.shippingSaas.service.impl;

import com.shipping.saas.shippingSaas.domain.WarehouseAddress;
import com.shipping.saas.shippingSaas.domain.dto.AddressesDTO;
import com.shipping.saas.shippingSaas.domain.dto.PhoneNumberDTO;
import com.shipping.saas.shippingSaas.domain.dto.WarehouseAddressDTO;
import com.shipping.saas.shippingSaas.repository.ClientRepository;
import com.shipping.saas.shippingSaas.repository.WarehouseRepository;
import com.shipping.saas.shippingSaas.service.WareHouseService;
import com.shipping.saas.shippingSaas.service.mapper.WarehouseAddressMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class WareHouseServiceImpl implements WareHouseService {

    private final WarehouseRepository warehouseRepository;
    private final PhoneNumberServiceImpl phoneNumberService;
    private final ClientRepository clientRepository;
    private final WarehouseAddressMapper warehouseAddressMapper;
    private final AddressesServiceImpl addressesService;


    @Override
    public WarehouseAddressDTO create(WarehouseAddressDTO warehouseAddressDTO) {
        log.info("Creating warehouse address for client {}", warehouseAddressDTO.getClient().getId());

        // 1️⃣ Convert DTO → Entity
        WarehouseAddress warehouseAddress = warehouseAddressMapper.toEntity(warehouseAddressDTO);

        // 2️⃣ Save parent first to get the generated UUID
        UUID clientId = warehouseAddressDTO.getClient().getId();
        warehouseAddress.setClient(clientRepository.getReferenceById(clientId));
        WarehouseAddress savedWarehouse = warehouseRepository.save(warehouseAddress);

        // 3️⃣ Save related phone numbers if present
        List<PhoneNumberDTO> savedPhoneNumbers = Collections.emptyList();

        if (warehouseAddressDTO.getPhoneNumbers() != null && !warehouseAddressDTO.getPhoneNumbers().isEmpty()) {
            List<PhoneNumberDTO> phonesToSave = new ArrayList<>(warehouseAddressDTO.getPhoneNumbers());
            phonesToSave.forEach(phone -> phone.setOwnerId(savedWarehouse.getId()));
            savedPhoneNumbers = phoneNumberService.createPhoneNumbers(phonesToSave);
        }

        // 4️⃣ Save address related to warehouse
        AddressesDTO savedAddress = null;
        if (warehouseAddressDTO.getAddress() != null) {
            AddressesDTO addressDTO = warehouseAddressDTO.getAddress();
            addressDTO.setAddressableId(savedWarehouse.getId());
            savedAddress = addressesService.createAddresses(addressDTO);
        }

        // 5️⃣ Map back to DTO for response
        WarehouseAddressDTO responseDTO = warehouseAddressMapper.toDto(savedWarehouse);
        responseDTO.setPhoneNumbers(savedPhoneNumbers);
        responseDTO.setAddress(savedAddress);

        return responseDTO;
    }


    @Override
    public WarehouseAddressDTO update(WarehouseAddressDTO warehouseAddress) {

        log.info("update warehouse address for client {}", warehouseAddress.getClient().getId());

        Optional<WarehouseAddressDTO> address = findByClientId(UUID.fromString(String.valueOf(warehouseAddress.getClient().getId())));

        if (address.isPresent()) {
            address.get().setName(warehouseAddress.getName());
        }
        WarehouseAddress update = warehouseAddressMapper.toEntity(warehouseAddress);

        return warehouseAddressMapper.toDto(warehouseRepository.save(update));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WarehouseAddressDTO> findById(UUID id) {

        log.info("find warehouse address for client {}", id);

        Optional<WarehouseAddress> address = warehouseRepository.findById(id);

        return Optional.of(warehouseAddressMapper.toDto(address.get()));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WarehouseAddressDTO> findByClientId(UUID id) {

        return Optional.of(warehouseAddressMapper
            .toDto(warehouseRepository.findByClientId(id)
                .orElseThrow(
                    () -> new RuntimeException("warehouse address not found"))));
    }

    @Override
    @Transactional(readOnly = true)
    public List<WarehouseAddressDTO> findAll() {
        return warehouseRepository
            .findAll()
            .stream()
            .map(warehouseAddressMapper::toDto)
            .toList();
    }
}
