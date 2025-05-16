package com.shipping.saas.shippingSaas.repository;

import com.shipping.saas.shippingSaas.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PermissionsRepository extends JpaRepository<Permission, UUID> {
}
