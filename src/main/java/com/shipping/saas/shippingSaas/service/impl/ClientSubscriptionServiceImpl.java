package com.shipping.saas.shippingSaas.service.impl;

import com.shipping.saas.shippingSaas.domain.clients.ClientSubscriptions;
import com.shipping.saas.shippingSaas.repository.ClientSubscriptionsRepository;
import com.shipping.saas.shippingSaas.service.ClientSubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientSubscriptionServiceImpl implements ClientSubscriptionService {

    private final ClientSubscriptionsRepository clientSubscriptionsRepository;

    @Override
    public ClientSubscriptions save(ClientSubscriptions clientSubscriptions) {

        log.debug("saving Client subscription : {}", clientSubscriptions);

        ClientSubscriptions subscription = ClientSubscriptions
            .builder()
            .client(clientSubscriptions.getClient())
            .billingCycle(clientSubscriptions.getBillingCycle())
            .notes(clientSubscriptions.getNotes())
            .startDate(clientSubscriptions.getStartDate())
            .build();

        return clientSubscriptionsRepository.save(subscription);
    }

    @Override
    public Optional<ClientSubscriptions> findById(UUID id) {
        return clientSubscriptionsRepository.findById(id);
    }

    @Override
    public List<ClientSubscriptions> findAll() {
        return clientSubscriptionsRepository.findAll();
    }

    @Override
    public List<ClientSubscriptions> findByClientId(UUID clientId) {
        return clientSubscriptionsRepository.findByClientId(clientId);
    }

    @Override
    public ClientSubscriptions deactivate(UUID id) {

        return clientSubscriptionsRepository.findById(id).map(sub -> {
            sub.setActive(false);
            sub.setEndDate(Instant.now());
            return clientSubscriptionsRepository.save(sub);
        }).orElseThrow(() -> new IllegalArgumentException("Subscription not found"));
    }


}
