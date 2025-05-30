package com.shipping.saas.shippingSaas.repository;

import com.shipping.saas.shippingSaas.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    Optional<Client> findByName(String clientName);
}
