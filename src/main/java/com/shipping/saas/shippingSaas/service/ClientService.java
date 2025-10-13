package com.shipping.saas.shippingSaas.service;

import com.shipping.saas.shippingSaas.domain.clients.Client;
import com.shipping.saas.shippingSaas.domain.dto.ClientDTO;

import java.util.List;
import java.util.UUID;

public interface ClientService {


    ClientDTO create(ClientDTO dto);

    ClientDTO update(UUID clientId, ClientDTO dto);

    ClientDTO findById(UUID clientId);

    ClientDTO findByName(String name);

    List<ClientDTO> findAll();

    void deleteById(UUID clientId);

}
