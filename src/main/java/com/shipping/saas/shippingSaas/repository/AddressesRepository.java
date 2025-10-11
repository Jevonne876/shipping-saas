package com.shipping.saas.shippingSaas.repository;

import com.shipping.saas.shippingSaas.domain.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressesRepository extends JpaRepository<Addresses, UUID> {


    List<Addresses> findAddressesByAddressableId(UUID ownerId);
}
