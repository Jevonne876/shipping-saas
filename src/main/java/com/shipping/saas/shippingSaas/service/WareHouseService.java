package com.shipping.saas.shippingSaas.service;

import com.shipping.saas.shippingSaas.domain.dto.WarehouseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WareHouseService {

    public WarehouseDTO create(WarehouseDTO warehouseAddress);

    public WarehouseDTO update(WarehouseDTO warehouseAddress);

    public Optional<WarehouseDTO> findById(UUID id);

    public Optional<WarehouseDTO> findByClientId(UUID id);

    public List<WarehouseDTO> findAll();


}
