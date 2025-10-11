package com.shipping.saas.shippingSaas.repository;

import com.shipping.saas.shippingSaas.domain.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AddressesRepository extends JpaRepository<Addresses, UUID> {


    List<Addresses> findAddressesByOwnerId(UUID ownerId);
}
