package com.shipping.saas.shippingSaas.service;

import com.shipping.saas.shippingSaas.domain.ClientUser;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ClientUserService {

    ClientUser save(ClientUser ClientUser);

    ClientUser updateUser(ClientUser ClientUser);

    Optional<ClientUser> findByEmail(String email);

    Optional<ClientUser> findById(String email);

    List<ClientUser> findAll();

    Page<ClientUser> findAllPageable();
}
