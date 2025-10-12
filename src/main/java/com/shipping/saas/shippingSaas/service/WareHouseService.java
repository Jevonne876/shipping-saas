package com.shipping.saas.shippingSaas.service;

import com.shipping.saas.shippingSaas.domain.WarehouseAddress;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WareHouseService {

    public WarehouseAddress create(WarehouseAddress warehouseAddress);

    public WarehouseAddress update(WarehouseAddress warehouseAddress);

    public Optional<WarehouseAddress> findById(UUID id);

    public Optional<WarehouseAddress> findByClientId(UUID id);

    public List<WarehouseAddress> findAll();


}
