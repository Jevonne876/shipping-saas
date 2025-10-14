package com.shipping.saas.shippingSaas.service.impl;

import com.shipping.saas.shippingSaas.domain.clients.Client;
import com.shipping.saas.shippingSaas.domain.clients.SubscriptionPlans;
import com.shipping.saas.shippingSaas.domain.dto.*;
import com.shipping.saas.shippingSaas.domain.enums.BillingCycle;
import com.shipping.saas.shippingSaas.exceptions.DuplicateResourceException;
import com.shipping.saas.shippingSaas.exceptions.PlatformUserNotFoundException;
import com.shipping.saas.shippingSaas.repository.ClientRepository;
import com.shipping.saas.shippingSaas.repository.SubscriptionPlanRepository;
import com.shipping.saas.shippingSaas.service.ClientService;
import com.shipping.saas.shippingSaas.service.mapper.ClientMapper;
import com.shipping.saas.shippingSaas.service.mapper.ClientSubscriptionsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    private final AddressesServiceImpl addressesService;

    private final PhoneNumberServiceImpl phoneNumberService;

    private final StoreServiceImpl storeService;

    private final WareHouseServiceImpl wareHouseService;

    private final ClientSubscriptionServiceImpl clientSubscriptionService;

    private final SubscriptionPlanRepository subscriptionPlanRepository;

    private final ClientSubscriptionsMapper clientSubscriptionsMapper;


    @Override
    public ClientDTO create(ClientDTO dto) {
        log.info("creating a new client");

        Client client = clientMapper.toEntity(dto);

        if (client.getTimeZone() == null) {
            client.setTimeZone("America/Jamaica");
        }

        Client savedClient = clientRepository.save(client);

        ClientDTO clientDTO = clientMapper.toDto(savedClient);

        AddressesDTO addressesDTO = new AddressesDTO();
        if (dto.getAddress() != null) {
            dto.getAddress().setAddressableId(savedClient.getId());
            dto.getAddress().setIsActive(true);
            addressesDTO = addressesService.createAddresses(dto.getAddress());
        }
        List<PhoneNumberDTO> phoneNumberDTOS = Collections.emptyList();
        if (dto.getPhoneNumbers() != null) {
            dto.getPhoneNumbers().stream().forEach((phoneNumber) -> {
                phoneNumber.setOwnerId(savedClient.getId());
                phoneNumber.setIsActive(true);
            });
            phoneNumberDTOS = phoneNumberService.createPhoneNumbers(dto.getPhoneNumbers());
        }


        List<StoreDTO> storeDTOS = Collections.emptyList();
        if (dto.getStores() != null) {
            dto.getStores().stream().forEach((store) -> {
                store.setClient(clientDTO);
            });
            storeDTOS = storeService.create(dto.getStores());
        }

        WarehouseDTO warehouseDTO = new WarehouseDTO();
        if (dto.getWarehouseAddress() != null) {
            dto.getWarehouseAddress().setClient(clientDTO);
            warehouseDTO = wareHouseService.create(dto.getWarehouseAddress());
        }

        ClientSubscriptionsDTO clientSubscriptionsDTO = new ClientSubscriptionsDTO();
        if (dto.getSubscriptionPlanId() != null && StringUtils.hasText(dto.getBillingCycle())) {
            clientSubscriptionsDTO = saveClientSubscriptions(dto, savedClient);
        }

        clientDTO.setWarehouseAddress(warehouseDTO);
        clientDTO.setPhoneNumbers(phoneNumberDTOS);
        clientDTO.setStores(storeDTOS);
        clientDTO.setAddress(addressesDTO);
        clientDTO.setSubscriptionPlanId(clientSubscriptionsDTO.getSubscriptionPlanId());
        clientDTO.setBillingCycle(clientSubscriptionsDTO.getBillingCycle());

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

    private ClientSubscriptionsDTO saveClientSubscriptions(ClientDTO clientDTO, Client client) {

        log.info("Saving client subscriptions for client with id {}", client.getId());

        SubscriptionPlans subscriptionPlans = subscriptionPlanRepository
            .findById(clientDTO
                .getSubscriptionPlanId())
            .orElseThrow(() -> new IllegalArgumentException("Subscription Plan not found"));

        ClientSubscriptionsDTO clientSubscriptionsDTO = new ClientSubscriptionsDTO();

        clientSubscriptionsDTO.setSubscriptionPlanId(subscriptionPlans.getId());
        clientSubscriptionsDTO.setClientId(client.getId());
        clientSubscriptionsDTO.setSubscriptionPlanName(subscriptionPlans.getName());

        clientSubscriptionsDTO.setStartDate(Instant.now());
        if (clientDTO.getBillingCycle().equals(BillingCycle.MONTHLY.toString())) {
            clientSubscriptionsDTO.setBillingCycle(BillingCycle.MONTHLY.toString());
            clientSubscriptionsDTO.setPriceAtSignup(subscriptionPlans.getMonthlyPrice());
        } else if (clientDTO.getBillingCycle().equals(BillingCycle.ANNUAL.toString())) {
            clientSubscriptionsDTO.setBillingCycle(BillingCycle.ANNUAL.toString());
            clientSubscriptionsDTO.setPriceAtSignup(subscriptionPlans.getAnnualPrice());
        } else {
            throw new RuntimeException("Invalid subscription plan");
        }

        clientSubscriptionsDTO.setIsActive(true);

        return clientSubscriptionService.save(clientSubscriptionsDTO);

    }
}
