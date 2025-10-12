package com.shipping.saas.shippingSaas.service.impl;

import com.shipping.saas.shippingSaas.domain.Store;
import com.shipping.saas.shippingSaas.repository.StoreRepository;
import com.shipping.saas.shippingSaas.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    public Store create(Store store) {
        log.info("creating new store for client {}", store.getClient().getId());

        Store newStore = Store.builder()
            .name(store.getName())
            .code(store.getCode())
            .client(store.getClient())
            .build();

        return storeRepository.save(newStore);
    }

    @Override
    public Store update(Store store) {
        log.info("updating store for client {}", store.getClient().getId());
        Optional<Store> updateStore = findById(store.getId());
        if (updateStore.isPresent()) {
            updateStore.get().setName(store.getName());
            updateStore.get().setCode(store.getCode());
            updateStore.get().setClient(store.getClient());
        }
        return storeRepository.save(updateStore.get());
    }

    @Override
    public Optional<Store> findById(UUID id) {
        log.info("finding store for client {}", id);
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Store with id " + id + " not found"));
        return Optional.of(store);
    }

    @Override
    public List<Store> findByClientId(UUID clientId) {
        log.info("finding store for client by client id {}", clientId);
        return storeRepository.findAllByClientId(clientId);
    }

    @Override
    public List<Store> findAll() {
        log.info("finding all stores");
        return storeRepository.findAll();
    }

    @Override
    public void delete(Store store) {
        log.info("deleting store for client {}", store.getClient().getId());

    }

    @Override
    public void deActivate(UUID id) {
        log.info("deactivating store for client {}", id);

    }
}
