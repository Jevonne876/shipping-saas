package com.shipping.saas.shippingSaas.service.impl;

import com.shipping.saas.shippingSaas.domain.clients.SubscriptionPlans;
import com.shipping.saas.shippingSaas.repository.SubscriptionPlanRepository;
import com.shipping.saas.shippingSaas.service.SubscriptionPlanService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Setter
@RequiredArgsConstructor
@Transactional
public class SubscriptionPlansServiceImpl implements SubscriptionPlanService {

    private final SubscriptionPlanRepository subscriptionPlanRepository;


    @Override
    public SubscriptionPlans createSubscriptionPlans(SubscriptionPlans subscriptionPlans) {
        log.info("creating subscription plans");

        return subscriptionPlanRepository.save(subscriptionPlans);
    }

    @Override
    public SubscriptionPlans updateSubscriptionPlans(SubscriptionPlans subscriptionPlans) {
        return null;
    }

    @Override
    public void deleteSubscriptionPlans(UUID subscriptionPlanId) {

    }

    @Override
    @Transactional(readOnly = true)
    public List<SubscriptionPlans> findAllSubscriptionPlans() {
        return List.of();
    }

    @Override
    public void deactivateSubscriptionPlans(UUID subscriptionPlanId) {

    }
}
