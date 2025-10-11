package com.shipping.saas.shippingSaas.repository;

import com.shipping.saas.shippingSaas.domain.PhoneNumbers;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumbers, UUID> {

    List<PhoneNumbers> findByOwnerId(UUID ownerId);


}
