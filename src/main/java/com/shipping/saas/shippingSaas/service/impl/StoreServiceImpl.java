package com.shipping.saas.shippingSaas.service.impl;

import com.shipping.saas.shippingSaas.domain.Store;
import com.shipping.saas.shippingSaas.domain.dto.AddressesDTO;
import com.shipping.saas.shippingSaas.domain.dto.PhoneNumberDTO;
import com.shipping.saas.shippingSaas.domain.dto.StoreDTO;
import com.shipping.saas.shippingSaas.repository.StoreRepository;
import com.shipping.saas.shippingSaas.service.StoreService;
import com.shipping.saas.shippingSaas.service.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final AddressesServiceImpl addressesServiceImpl;
    private final PhoneNumberServiceImpl phoneNumberService;
    private final StoreMapper storeMapper;

    @Override
    public StoreDTO create(StoreDTO storeDTO) {

        log.info("Creating new store for client {}", storeDTO.getClient().getId());

        //Map to entity
        Store store = storeMapper.toEntity(storeDTO);

        //Save the store first to generate its ID
        Store savedStore = storeRepository.save(store);

        //If there are phone numbers, attach store ID as ownerId and save them
        List<PhoneNumberDTO> phoneNumberDTOS = Collections.emptyList();
        if (storeDTO.getPhoneNumbers() != null && !storeDTO.getPhoneNumbers().isEmpty()) {
            storeDTO.getPhoneNumbers().forEach(phone -> phone.setOwnerId(savedStore.getId()));
            phoneNumberDTOS = phoneNumberService.createPhoneNumbers(storeDTO.getPhoneNumbers());
        }

        //If there is a address related to the store
        AddressesDTO addressesDTO = new AddressesDTO();
        if (storeDTO.getAddresses() != null) {
            storeDTO.getAddresses().setAddressableId(savedStore.getId());

            addressesDTO = addressesServiceImpl.createAddresses(storeDTO.getAddresses());
        }

        StoreDTO responseDTO = storeMapper.toDto(savedStore);
        responseDTO.setPhoneNumbers(phoneNumberDTOS);
        responseDTO.setAddresses(addressesDTO);

        //Return the saved store as DTO
        return storeMapper.toDto(savedStore);
    }

    @Override
    public List<StoreDTO> create(List<StoreDTO> storeDTOs) {
        log.info("Creating {} store(s) for client {}", storeDTOs.size(),
            storeDTOs.isEmpty() ? "N/A" : storeDTOs.get(0).getClient().getId());

        List<StoreDTO> savedStores = new ArrayList<>();

        for (StoreDTO storeDTO : storeDTOs) {

            // 1️⃣ Convert DTO → Entity
            Store store = storeMapper.toEntity(storeDTO);
            store.setActive(true);

            // 2️⃣ Save parent store first to generate UUID
            Store savedStore = storeRepository.save(store);

            // 3️⃣ Save related phone numbers if present
            List<PhoneNumberDTO> savedPhoneNumbers = Collections.emptyList();
            if (storeDTO.getPhoneNumbers() != null && !storeDTO.getPhoneNumbers().isEmpty()) {
                List<PhoneNumberDTO> phonesToSave = new ArrayList<>(storeDTO.getPhoneNumbers());
                phonesToSave.forEach(phone -> phone.setOwnerId(savedStore.getId()));
                savedPhoneNumbers = phoneNumberService.createPhoneNumbers(phonesToSave);
            }

            // 4️⃣ Save related address if present
            AddressesDTO savedAddress = null;
            if (storeDTO.getAddresses() != null) {
                AddressesDTO addressDTO = storeDTO.getAddresses();
                addressDTO.setAddressableId(savedStore.getId());
                savedAddress = addressesServiceImpl.createAddresses(addressDTO);
            }

            // 5️⃣ Map saved entity back to DTO
            StoreDTO responseDTO = storeMapper.toDto(savedStore);
            responseDTO.setPhoneNumbers(savedPhoneNumbers);
            responseDTO.setAddresses(savedAddress);

            // 6️⃣ Add to result list
            savedStores.add(responseDTO);
        }

        return savedStores;
    }


    @Override
    public StoreDTO update(StoreDTO store) {
        log.info("updating store for client {}", store.getClient().getId());
        Optional<StoreDTO> updateStore = findById(store.getId());
        if (updateStore.isPresent()) {
            updateStore.get().setName(store.getName());
            updateStore.get().setCode(store.getCode());
            updateStore.get().setClient(store.getClient());
        }

        Store updated = storeRepository.save(storeMapper.toEntity(updateStore.get()));

        return storeMapper.toDto(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StoreDTO> findById(UUID id) {
        log.info("finding store for client {}", id);

        return Optional.
            of(storeMapper.toDto(storeRepository.findById(id)
                .orElseThrow(
                    () -> new IllegalArgumentException("Store with id " + id + " not found")
                )));
    }

    @Override
    @Transactional(readOnly = true)
    public List<StoreDTO> findByClientId(UUID clientId) {
        log.info("finding store for client by client id {}", clientId);
        return storeRepository.findAllByClientId(clientId)
            .stream()
            .map(storeMapper::toDto)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<StoreDTO> findAll() {
        log.info("finding all stores");
        return storeRepository.findAll()
            .stream()
            .map(storeMapper::toDto)
            .toList();
    }

    @Override
    public void delete(StoreDTO store) {
        log.info("deleting store for client {}", store.getClient().getId());

    }

    @Override
    public void deActivate(UUID id) {
        log.info("deactivating store for client {}", id);

    }
}
