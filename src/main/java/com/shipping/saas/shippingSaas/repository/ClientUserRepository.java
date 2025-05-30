package com.shipping.saas.shippingSaas.repository;

import com.shipping.saas.shippingSaas.domain.ClientUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientUserRepository extends JpaRepository<ClientUser, UUID> {

    Optional<ClientUser> findByEmail(String email);
}
