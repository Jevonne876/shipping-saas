package com.shipping.saas.shippingSaas.service;

import com.shipping.saas.shippingSaas.domain.clients.SubscriptionPlans;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;



public interface SubscriptionPlanService {

    SubscriptionPlans createSubscriptionPlans(SubscriptionPlans subscriptionPlans);

    SubscriptionPlans updateSubscriptionPlans(SubscriptionPlans subscriptionPlans);

    void deleteSubscriptionPlans(UUID subscriptionPlanId);

    List<SubscriptionPlans> findAllSubscriptionPlans();

    void deactivateSubscriptionPlans(UUID subscriptionPlanId);


}
