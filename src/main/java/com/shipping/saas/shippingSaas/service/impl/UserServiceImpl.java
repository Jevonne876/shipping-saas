package com.shipping.saas.shippingSaas.service.impl;

import com.shipping.saas.shippingSaas.domain.PlatformUser;
import com.shipping.saas.shippingSaas.domain.Role;
import com.shipping.saas.shippingSaas.domain.dto.PlatformUserDTO;
import com.shipping.saas.shippingSaas.domain.dto.UserType;
import com.shipping.saas.shippingSaas.repository.PlatformUserRepository;
import com.shipping.saas.shippingSaas.repository.RoleRepository;
import com.shipping.saas.shippingSaas.service.PlatformUserService;
import com.shipping.saas.shippingSaas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PlatformUserRepository platformUserRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final RoleRepository roleRepository;

    @Override
    public PlatformUser save(PlatformUserDTO newUser) throws Exception {

        PlatformUser platformUser = new PlatformUser();

        Role role = roleRepository.findByName(newUser.getRole())
            .orElseThrow(() -> new Exception("Role not found."));


        platformUser.setUserType(UserType.PLATFORM_USER.name());
        platformUser.setFirstName(newUser.getFirstName());
        platformUser.setLastName(newUser.getLastName());
        platformUser.setEmail(newUser.getEmail());
        platformUser.setPhoneNumber(newUser.getPhoneNumber());
        platformUser.setPasswordHash(passwordEncoder.encode(newUser.getPasswordHash()));
        platformUser.setRoleId(role);
        platformUser.setStreetAddress(newUser.getStreetAddress());
        platformUser.setCity(newUser.getCity());
        platformUser.setState(newUser.getState());
        platformUser.setPostalCode(newUser.getPostalCode());
        platformUser.setCountry(newUser.getCountry());

        return platformUserRepository.save(platformUser);
    }

    @Override
    public PlatformUser update(String id, PlatformUserService platformUserService) {
        return null;
    }

    @Override
    public Optional<PlatformUser> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<PlatformUser> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<PlatformUser> findByUsername(String userName) {
        return Optional.empty();
    }


}
