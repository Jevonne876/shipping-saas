package com.shipping.saas.shippingSaas.repository;

import com.shipping.saas.shippingSaas.domain.ClientUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientUserRepository extends JpaRepository<ClientUser, UUID> {

    Optional<ClientUser> findByEmail(String email);
}
