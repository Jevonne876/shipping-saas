package com.shipping.saas.shippingSaas.repository;

import com.shipping.saas.shippingSaas.domain.PickUpLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PickUpLocationRepository extends JpaRepository<PickUpLocation, UUID> {
}
