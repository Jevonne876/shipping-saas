package com.shipping.saas.shippingSaas.repository;

import com.shipping.saas.shippingSaas.domain.WarehouseAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WarehouseRepository extends JpaRepository<WarehouseAddress, UUID> {
}
