package com.shipping.saas.shippingSaas.repository;

import com.shipping.saas.shippingSaas.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
