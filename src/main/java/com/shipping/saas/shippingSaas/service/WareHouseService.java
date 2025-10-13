package com.shipping.saas.shippingSaas.service;

import com.shipping.saas.shippingSaas.domain.WarehouseAddress;
import com.shipping.saas.shippingSaas.domain.dto.WarehouseAddressDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WareHouseService {

    public WarehouseAddressDTO create(WarehouseAddressDTO warehouseAddress);

    public WarehouseAddressDTO update(WarehouseAddressDTO warehouseAddress);

    public Optional<WarehouseAddressDTO> findById(UUID id);

    public Optional<WarehouseAddressDTO> findByClientId(UUID id);

    public List<WarehouseAddressDTO> findAll();


}
