package com.shipping.saas.shippingSaas.service;

import com.shipping.saas.shippingSaas.domain.PlatformUser;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {


    PlatformUser save(PlatformUser platformUser);

    PlatformUser updateUser(PlatformUser platformUser);

    Optional<PlatformUser> findByEmail(String email);

    Optional<PlatformUser> findById(String email);

    List<PlatformUser> findAll();

    Page<PlatformUser> findAllPageable();

}
