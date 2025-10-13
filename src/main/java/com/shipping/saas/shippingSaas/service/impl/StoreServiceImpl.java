package com.shipping.saas.shippingSaas.service.impl;

import com.shipping.saas.shippingSaas.domain.Store;
import com.shipping.saas.shippingSaas.domain.dto.StoreDTO;
import com.shipping.saas.shippingSaas.repository.StoreRepository;
import com.shipping.saas.shippingSaas.service.StoreService;
import com.shipping.saas.shippingSaas.service.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    @Override
    public StoreDTO create(StoreDTO store) {

        log.info("creating new store for client {}", store.getClient().getId());

        Store newStore = storeMapper.toEntity(store);

        return storeMapper.toDto(storeRepository.save(newStore));
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
