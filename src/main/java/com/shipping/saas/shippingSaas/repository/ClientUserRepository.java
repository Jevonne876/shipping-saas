package com.shipping.saas.shippingSaas.repository;

import com.shipping.saas.shippingSaas.domain.ClientUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientUserRepository extends JpaRepository<ClientUser, Long> {

    Optional<ClientUser> findByEmail(String email);
}
