package com.shipping.saas.shippingSaas.service;

import com.shipping.saas.shippingSaas.domain.clients.Client;

import java.util.List;
import java.util.UUID;

public interface ClientService {


    Client create(Client client);

    Client update(UUID clientId, Client client);

    Client findById(UUID clientId);

    Client findByName(String name);

    List<Client> findAll();

    void deleteById(UUID clientId);

}
