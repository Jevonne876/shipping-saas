package com.shipping.saas.shippingSaas.service;

import com.shipping.saas.shippingSaas.domain.clients.ClientSubscriptions;
import com.shipping.saas.shippingSaas.domain.dto.ClientSubscriptionsDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientSubscriptionService {


    public ClientSubscriptionsDTO save(ClientSubscriptionsDTO dto);

    public Optional<ClientSubscriptionsDTO> findById(UUID id);

    public List<ClientSubscriptionsDTO> findAll();

    public List<ClientSubscriptionsDTO> findByClientId(UUID clientId);

    public ClientSubscriptionsDTO deactivate(UUID id);




}
