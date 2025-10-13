package com.shipping.saas.shippingSaas.service.impl;

import com.shipping.saas.shippingSaas.domain.clients.Client;
import com.shipping.saas.shippingSaas.domain.clients.ClientSubscriptions;
import com.shipping.saas.shippingSaas.domain.clients.SubscriptionPlans;
import com.shipping.saas.shippingSaas.domain.dto.ClientSubscriptionsDTO;
import com.shipping.saas.shippingSaas.repository.ClientRepository;
import com.shipping.saas.shippingSaas.repository.ClientSubscriptionsRepository;
import com.shipping.saas.shippingSaas.repository.SubscriptionPlanRepository;
import com.shipping.saas.shippingSaas.service.ClientSubscriptionService;


import com.shipping.saas.shippingSaas.service.mapper.ClientSubscriptionsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ClientSubscriptionServiceImpl implements ClientSubscriptionService {

    private final ClientSubscriptionsRepository clientSubscriptionsRepository;
    private final ClientRepository clientRepository;
    private final SubscriptionPlanRepository subscriptionPlansRepository;
    private final ClientSubscriptionsMapper clientSubscriptionsMapper;


    @Override
    public ClientSubscriptionsDTO save(ClientSubscriptionsDTO dto) {

        log.debug("saving Client subscription : {}", dto);

        // Fetch references
        Client client = clientRepository.findById(dto.getClientId())
            .orElseThrow(() -> new IllegalArgumentException("Client not found"));
        SubscriptionPlans plan = subscriptionPlansRepository.findById(dto.getSubscriptionPlanId())
            .orElseThrow(() -> new IllegalArgumentException("Subscription plan not found"));

        // Deactivate existing subscriptions for that client
        clientSubscriptionsRepository.findByClientId(client.getId()).forEach(sub -> {
            sub.setActive(false);
        });

        // Create new subscription
        ClientSubscriptions newSub = clientSubscriptionsMapper.toEntity(dto);
        newSub.setClient(client);
        newSub.setSubscriptionPlan(plan);

        ClientSubscriptions saved = clientSubscriptionsRepository.save(newSub);
        return clientSubscriptionsMapper.toDto(saved);

    }


    @Override
    public Optional<ClientSubscriptionsDTO> findById(UUID id) {

        ClientSubscriptions subscription = clientSubscriptionsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("client subscription not found"));

        return Optional.of(clientSubscriptionsMapper.toDto(subscription));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientSubscriptionsDTO> findAll() {
        log.debug("Fetching all client subscriptions");

        return clientSubscriptionsRepository.findAll().stream()
            .map(clientSubscriptionsMapper::toDto)
            .toList();
    }

    /**
     * 🔹 Get all subscriptions for a client
     */
    public List<ClientSubscriptionsDTO> findByClientId(UUID clientId) {
        List<ClientSubscriptions> list = clientSubscriptionsRepository.findByClientId(clientId);
        return list.stream()
            .map(clientSubscriptionsMapper::toDto)
            .toList();
    }

    /**
     * 🔹 Deactivate a subscription manually (optional)
     */
    public ClientSubscriptionsDTO deactivate(UUID id) {
        ClientSubscriptions sub = clientSubscriptionsRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Subscription not found"));
        sub.setActive(false);
        sub.setEndDate(Instant.now());
        ClientSubscriptions saved = clientSubscriptionsRepository.save(sub);
        return clientSubscriptionsMapper.toDto(saved);
    }


}
