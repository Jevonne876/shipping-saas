package com.shipping.saas.shippingSaas.service;

import com.shipping.saas.shippingSaas.domain.Store;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StoreService {


    public Store create(Store store);

    public Store update(Store store);

    public Optional<Store> findById(UUID id);

    public List<Store> findByClientId(UUID clientId);

    public List<Store> findAll();

    public void delete(Store store);

    public void deActivate(UUID id);


}
