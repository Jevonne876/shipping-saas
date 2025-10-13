package com.shipping.saas.shippingSaas.service.impl;

import com.shipping.saas.shippingSaas.domain.clients.Client;
import com.shipping.saas.shippingSaas.domain.dto.ClientDTO;
import com.shipping.saas.shippingSaas.exceptions.DuplicateResourceException;
import com.shipping.saas.shippingSaas.exceptions.PlatformUserNotFoundException;
import com.shipping.saas.shippingSaas.repository.ClientRepository;
import com.shipping.saas.shippingSaas.service.ClientService;
import com.shipping.saas.shippingSaas.service.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientDTO create(ClientDTO dto) {
        log.info("creating a new client");

        Client client = clientMapper.toEntity(dto);

        ClientDTO clientDTO = clientMapper.toDto(clientRepository.save(client));

        return clientDTO;
    }

    @Override
    public ClientDTO update(UUID clientId, ClientDTO dto) {

        log.info("Updating client with id {}", clientId);

        // 1️⃣ Fetch entity
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new PlatformUserNotFoundException("Client with id " + clientId + " not found"));

        // 2️⃣ Check for duplicate email
        if (clientRepository.existsByEmailAndIdNot(dto.getEmail(), clientId)) {
            throw new DuplicateResourceException("Client with email " + dto.getEmail() + " already exists");
        }

        // 3️⃣ Update mutable fields
        client.setEmail(dto.getEmail());
        client.setName(dto.getName());
        client.setCompanyCode(dto.getCompanyCode());

        // 4️⃣ Save entity
        Client updatedClient = clientRepository.save(client);

        // 5️⃣ Convert back to DTO
        return clientMapper.toDto(updatedClient);
    }


    @Override
    @Transactional(readOnly = true)
    public ClientDTO findById(UUID clientId) {
        log.info("Fetching client with id {}", clientId);

        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new PlatformUserNotFoundException(
                String.format("Client with id %s not found", clientId)
            ));

        return clientMapper.toDto(client);
    }


    @Override
    @Transactional(readOnly = true)
    public ClientDTO findByName(String name) {

        log.info("Fetching client with name {}", name);

        Client client = clientRepository.findByName(name)
            .orElseThrow(() -> new PlatformUserNotFoundException("Client not found"));

        return clientMapper.toDto(client);
    }


    @Override
    @Transactional(readOnly = true)
    public List<ClientDTO> findAll() {
        log.info("Fetching all clients");

        return clientRepository.
            findAll()
            .stream().map(clientMapper::toDto)
            .toList();
    }

    //Todo
    @Override
    public void deleteById(UUID clientId) {

    }
}
