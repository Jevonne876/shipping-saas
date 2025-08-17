package com.shipping.saas.shippingSaas.service.impl;

import com.shipping.saas.shippingSaas.domain.Client;
import com.shipping.saas.shippingSaas.exceptions.DuplicateResourceException;
import com.shipping.saas.shippingSaas.exceptions.PlatformUserNotFoundException;
import com.shipping.saas.shippingSaas.repository.ClientRepository;
import com.shipping.saas.shippingSaas.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
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

        log.info("updating client with id {}", clientId);

        Client savedClient = clientRepository.findById(clientId).orElseThrow(
            () -> new PlatformUserNotFoundException("client with id {} not found " + clientId + " not found"));

        //checks if email already exists
        if (clientRepository.existsByEmailAndIdNot(client.getEmail(), clientId)) {
            throw new DuplicateResourceException("client with id " + clientId + " already exists");
        }

        //checks if phone number already exists
        if (clientRepository.existsByPhoneNumberAndIdNot(client.getPhoneNumber(), clientId)) {
            throw new DuplicateResourceException("client with id " + clientId + " already exists");
        }

        savedClient.setName(client.getName());
        savedClient.setEmail(client.getEmail());
        savedClient.setPhoneNumber(client.getPhoneNumber());
        savedClient.setCompanyCode(client.getCompanyCode());
        savedClient.setSubscriptionPlan(client.getSubscriptionPlan());
        savedClient.setLogoUrl(client.getLogoUrl());
        savedClient.setStreetAddress(client.getStreetAddress());
        savedClient.setCity(client.getCity());
        savedClient.setState(client.getState());
        savedClient.setCountry(client.getCountry());
        savedClient.setPostalCode(client.getPostalCode());
        return clientRepository.save(savedClient);

    }

    @Override
    public Client findById(UUID clientId) {
        return clientRepository.findById(clientId)
            .orElseThrow(() -> new PlatformUserNotFoundException("Client not found"));
    }

    @Override
    public Client findByName(String name) {
        return clientRepository.findByName(name)
            .orElseThrow(() -> new PlatformUserNotFoundException("Client not found"));
    }

    @Override
    public List<Client> findAll() {

        return clientRepository.findAll();
    }

    @Override
    public void deleteById(UUID clientId) {
        

    }
}
