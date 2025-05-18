package com.shipping.saas.shippingSaas.service.impl;

import com.shipping.saas.shippingSaas.domain.ClientUser;
import com.shipping.saas.shippingSaas.service.ClientUserService;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public class ClientUserServiceImpl implements ClientUserService {

    @Override
    public ClientUser save(ClientUser ClientUser) {
        return null;
    }

    @Override
    public ClientUser updateUser(ClientUser ClientUser) {
        return null;
    }

    @Override
    public Optional<ClientUser> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<ClientUser> findById(String email) {
        return Optional.empty();
    }

    @Override
    public List<ClientUser> findAll() {
        return List.of();
    }

    @Override
    public Page<ClientUser> findAllPageable() {
        return null;
    }
}
