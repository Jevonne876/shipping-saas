package com.shipping.saas.shippingSaas.service;

import com.shipping.saas.shippingSaas.domain.clients.ClientSubscriptions;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientSubscriptionService {


    public ClientSubscriptions save(ClientSubscriptions clientSubscriptions);

    public Optional<ClientSubscriptions> findById(UUID id);

    public List<ClientSubscriptions> findAll();

    public List<ClientSubscriptions> findByClientId(UUID clientId);

    public ClientSubscriptions deactivate(UUID id);




}
