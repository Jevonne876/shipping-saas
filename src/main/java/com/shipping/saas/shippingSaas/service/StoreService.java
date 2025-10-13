package com.shipping.saas.shippingSaas.service;

import com.shipping.saas.shippingSaas.domain.Store;
import com.shipping.saas.shippingSaas.domain.dto.StoreDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StoreService {


    public StoreDTO create(StoreDTO store);

    public StoreDTO update(StoreDTO store);

    public Optional<StoreDTO> findById(UUID id);

    public List<StoreDTO> findByClientId(UUID clientId);

    public List<StoreDTO> findAll();

    public void delete(StoreDTO store);

    public void deActivate(UUID id);


}
