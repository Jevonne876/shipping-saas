package com.shipping.saas.shippingSaas.service.impl;

import com.shipping.saas.shippingSaas.domain.WarehouseAddress;
import com.shipping.saas.shippingSaas.repository.WarehouseRepository;
import com.shipping.saas.shippingSaas.service.WareHouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class WareHouseServiceImpl implements WareHouseService {

    private final WarehouseRepository warehouseRepository;

    @Override
    public WarehouseAddress create(WarehouseAddress warehouseAddress) {
        log.info("create warehouse address for client {}", warehouseAddress.getClient().getId());

        WarehouseAddress address = WarehouseAddress.builder()
            .name(warehouseAddress.getName())
            .client(warehouseAddress.getClient())
            .label(warehouseAddress.getLabel())
            .clientCustomers(warehouseAddress.getClientCustomers())
            .build();
        return warehouseRepository.save(address);
    }

    @Override
    public WarehouseAddress update(WarehouseAddress warehouseAddress) {
        log.info("update warehouse address for client {}", warehouseAddress.getClient().getId());
        Optional<WarehouseAddress> address = findByClientId(warehouseAddress.getClient().getId());
        if (address.isPresent()) {
            address.get().setName(warehouseAddress.getName());
            address.get().setLabel(warehouseAddress.getLabel());
            address.get().setClientCustomers(warehouseAddress.getClientCustomers());

        }
        return warehouseRepository.save(address.get());
    }

    @Override
    public Optional<WarehouseAddress> findById(UUID id) {
        log.info("find warehouse address for client {}", id);
        Optional<WarehouseAddress> address = warehouseRepository.findById(id);
        return Optional.ofNullable(address.get());
    }

    @Override
    public Optional<WarehouseAddress> findByClientId(UUID id) {
        WarehouseAddress warehouseAddress = warehouseRepository.findByClientId(id).orElseThrow(() -> new RuntimeException("warehouse address not found"));
        return Optional.of(warehouseAddress);
    }

    @Override
    public List<WarehouseAddress> findAll() {
        return warehouseRepository.findAll();
    }
}
