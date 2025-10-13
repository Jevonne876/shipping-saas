package com.shipping.saas.shippingSaas.service.impl;

import com.shipping.saas.shippingSaas.domain.WarehouseAddress;
import com.shipping.saas.shippingSaas.domain.dto.WarehouseAddressDTO;
import com.shipping.saas.shippingSaas.repository.WarehouseRepository;
import com.shipping.saas.shippingSaas.service.WareHouseService;
import com.shipping.saas.shippingSaas.service.mapper.WarehouseAddressMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class WareHouseServiceImpl implements WareHouseService {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseAddressMapper warehouseAddressMapper;

    @Override
    public WarehouseAddressDTO create(WarehouseAddressDTO warehouseAddress) {

        log.info("create warehouse address for client {}", warehouseAddress.getClient().getId());

        WarehouseAddress address = warehouseAddressMapper.toEntity(warehouseAddress);

        return warehouseAddressMapper.toDto(warehouseRepository.save(address));
    }

    @Override
    public WarehouseAddressDTO update(WarehouseAddressDTO warehouseAddress) {

        log.info("update warehouse address for client {}", warehouseAddress.getClient().getId());

        Optional<WarehouseAddressDTO> address = findByClientId(UUID.fromString(warehouseAddress.getClient().getId()));

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
