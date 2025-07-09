package com.shipping.saas.shippingSaas.repository;

import com.shipping.saas.shippingSaas.domain.PickUpLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PickUpLocationRepository extends JpaRepository<PickUpLocation, UUID> {
}
