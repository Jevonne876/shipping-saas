package com.shipping.saas.shippingSaas.service.impl;

import com.shipping.saas.shippingSaas.domain.Client;
import com.shipping.saas.shippingSaas.repository.ClientRepository;
import com.shipping.saas.shippingSaas.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {


    private final ClientRepository clientRepository;


    @Override
    public Client create(Client client) {
        Client newClient = Client.builder()
            .name(client.getName())
            .email(client.getEmail())
            .phoneNumber(client.getPhoneNumber())
            .companyCode(client.getCompanyCode())
            .subscriptionPlan(client.getSubscriptionPlan())
            .logoUrl(client.getLogoUrl())
            .streetAddress(client.getStreetAddress())
            .city(client.getCity())
            .state(client.getState())
            .country(client.getCountry())
            .postalCode(client.getPostalCode())
            .build();

        return clientRepository.save(newClient);
    }


    @Override
    public Client update(UUID clientId, Client client) {
        return null;
    }

    @Override
    public Client findById(UUID clientId) {
        return clientRepository.findById(clientId)
            .orElseThrow(() -> new IllegalArgumentException("Client not found"));
    }

    @Override
    public Client findByName(String name) {
        return clientRepository.findByName(name)
            .orElseThrow(() -> new IllegalArgumentException("Client not found"));
    }

    @Override
    public void deleteById(UUID clientId) {

    }
}
