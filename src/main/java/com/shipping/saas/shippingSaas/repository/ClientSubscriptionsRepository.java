package com.shipping.saas.shippingSaas.repository;

import com.shipping.saas.shippingSaas.domain.clients.ClientSubscriptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientSubscriptionsRepository extends JpaRepository<ClientSubscriptions, UUID> {

    List<ClientSubscriptions> findByClientId(UUID clientId);
    List<ClientSubscriptions> findByIsActiveTrue();
}
