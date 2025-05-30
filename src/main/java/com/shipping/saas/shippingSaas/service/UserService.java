package com.shipping.saas.shippingSaas.service;

import com.shipping.saas.shippingSaas.domain.PlatformUser;
import com.shipping.saas.shippingSaas.domain.dto.PlatformUserDTO;

import java.util.Optional;

public interface UserService {


    PlatformUser save(PlatformUserDTO newUser) throws Exception;

    PlatformUser update(String id, PlatformUserService platformUserService);

    Optional<PlatformUser> findById(String id);

    Optional<PlatformUser> findByEmail(String email);

    Optional<PlatformUser> findByUsername(String userName);

}
