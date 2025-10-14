package com.shipping.saas.shippingSaas.service.impl;

import com.shipping.saas.shippingSaas.domain.Warehouse;
import com.shipping.saas.shippingSaas.domain.dto.AddressesDTO;
import com.shipping.saas.shippingSaas.domain.dto.PhoneNumberDTO;
import com.shipping.saas.shippingSaas.domain.dto.WarehouseDTO;
import com.shipping.saas.shippingSaas.repository.ClientRepository;
import com.shipping.saas.shippingSaas.repository.WarehouseRepository;
import com.shipping.saas.shippingSaas.service.WareHouseService;
import com.shipping.saas.shippingSaas.service.mapper.WarehouseMapper;
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
    private final WarehouseMapper warehouseMapper;
    private final AddressesServiceImpl addressesService;


    @Override
    public WarehouseDTO create(WarehouseDTO warehouseDTO) {
        log.info("Creating warehouse address for client {}", warehouseDTO.getClient().getId());

        // 1️⃣ Convert DTO → Entity
        Warehouse warehouse = warehouseMapper.toEntity(warehouseDTO);

        // 2️⃣ Save parent first to get the generated UUID
        UUID clientId = warehouseDTO.getClient().getId();
        warehouse.setClient(clientRepository.getReferenceById(clientId));
        Warehouse savedWarehouse = warehouseRepository.save(warehouse);

        // 3️⃣ Save related phone numbers if present
        List<PhoneNumberDTO> savedPhoneNumbers = Collections.emptyList();

        if (warehouseDTO.getPhoneNumbers() != null && !warehouseDTO.getPhoneNumbers().isEmpty()) {
            List<PhoneNumberDTO> phonesToSave = new ArrayList<>(warehouseDTO.getPhoneNumbers());
            phonesToSave.forEach(phone -> phone.setOwnerId(savedWarehouse.getId()));
            savedPhoneNumbers = phoneNumberService.createPhoneNumbers(phonesToSave);
        }

        // 4️⃣ Save address related to warehouse
        AddressesDTO savedAddress = null;
        if (warehouseDTO.getAddress() != null) {
            AddressesDTO addressDTO = warehouseDTO.getAddress();
            addressDTO.setAddressableId(savedWarehouse.getId());
            savedAddress = addressesService.createAddresses(addressDTO);
        }

        // 5️⃣ Map back to DTO for response
        WarehouseDTO responseDTO = warehouseMapper.toDto(savedWarehouse);
        responseDTO.setPhoneNumbers(savedPhoneNumbers);
        responseDTO.setAddress(savedAddress);

        return responseDTO;
    }


    @Override
    public WarehouseDTO update(WarehouseDTO warehouseAddress) {

        log.info("update warehouse address for client {}", warehouseAddress.getClient().getId());

        Optional<WarehouseDTO> address = findByClientId(UUID.fromString(String.valueOf(warehouseAddress.getClient().getId())));

        if (address.isPresent()) {
            address.get().setName(warehouseAddress.getName());
        }
        Warehouse update = warehouseMapper.toEntity(warehouseAddress);

        return warehouseMapper.toDto(warehouseRepository.save(update));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WarehouseDTO> findById(UUID id) {

        log.info("find warehouse address for client {}", id);

        Optional<Warehouse> address = warehouseRepository.findById(id);

        return Optional.of(warehouseMapper.toDto(address.get()));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WarehouseDTO> findByClientId(UUID id) {

        return Optional.of(warehouseMapper
            .toDto(warehouseRepository.findByClientId(id)
                .orElseThrow(
                    () -> new RuntimeException("warehouse address not found"))));
    }

    @Override
    @Transactional(readOnly = true)
    public List<WarehouseDTO> findAll() {
        return warehouseRepository
            .findAll()
            .stream()
            .map(warehouseMapper::toDto)
            .toList();
    }
}
