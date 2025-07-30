package com.shipping.saas.shippingSaas.service;

import com.shipping.saas.shippingSaas.domain.PlatformUser;
import com.shipping.saas.shippingSaas.domain.dto.PlatformUserDTO;
import com.shipping.saas.shippingSaas.exceptions.BadRequestException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {


    PlatformUser save(PlatformUserDTO newUser) throws Exception;

    PlatformUser update(UUID id, PlatformUserDTO newUser) throws Exception;

    List<PlatformUser> findAll();

    Optional<PlatformUser> findById(String id);

    Optional<PlatformUser> findByEmail(String email);

    Optional<PlatformUser> findByUsername(String userName);

}
