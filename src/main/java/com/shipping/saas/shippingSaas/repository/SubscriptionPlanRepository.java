package com.shipping.saas.shippingSaas.repository;

import com.shipping.saas.shippingSaas.domain.clients.SubscriptionPlans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlans, UUID> {
}
