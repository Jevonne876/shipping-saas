package com.shipping.saas.shippingSaas.service;

import com.shipping.saas.shippingSaas.domain.Client;

import java.util.Optional;
import java.util.UUID;

public interface ClientService {


    Client create(Client client);

    Client update(UUID clientId, Client client);

    Client findById(UUID clientId);

    Client findByName(String name);

    void deleteById(UUID clientId);

}
