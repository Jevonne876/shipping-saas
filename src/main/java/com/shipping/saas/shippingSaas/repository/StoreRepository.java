package com.shipping.saas.shippingSaas.repository;

import com.shipping.saas.shippingSaas.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<Store, UUID> {

    List<Store> findAllByClientId(UUID clientId);


}
