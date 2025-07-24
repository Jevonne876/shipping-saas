package com.shipping.saas.shippingSaas.service;

import com.shipping.saas.shippingSaas.domain.PlatformUser;
import com.shipping.saas.shippingSaas.domain.dto.PlatformUserDTO;
import com.shipping.saas.shippingSaas.service.impl.PlatformUserService;

import java.util.List;
import java.util.Optional;

public interface UserService {


    PlatformUser save(PlatformUserDTO newUser) throws Exception;

    PlatformUser update(String id, PlatformUserService platformUserService);

    List<PlatformUser> findAll();

    Optional<PlatformUser> findById(String id);

    Optional<PlatformUser> findByEmail(String email);

    Optional<PlatformUser> findByUsername(String userName);

}
