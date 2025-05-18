package com.shipping.saas.shippingSaas.service.impl;


import com.shipping.saas.shippingSaas.domain.Permission;
import com.shipping.saas.shippingSaas.domain.PlatformUser;
import com.shipping.saas.shippingSaas.domain.Role;
import com.shipping.saas.shippingSaas.domain.dto.UserType;
import com.shipping.saas.shippingSaas.repository.PermissionsRepository;
import com.shipping.saas.shippingSaas.repository.PlatformUserRepository;
import com.shipping.saas.shippingSaas.repository.RoleRepository;
import com.shipping.saas.shippingSaas.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {


    private final PlatformUserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PermissionsRepository permissionsRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(PlatformUserRepository userRepository, RoleRepository roleRepository, PermissionsRepository permissionsRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.permissionsRepository = permissionsRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public PlatformUser save(PlatformUser platformUser) {
        // 1. Create and save permission
        Permission permission = new Permission();
        permission.setName("CREATE");
        permission.setDescription("can create");

        Permission savedPermission = permissionsRepository.save(permission); // returns a managed entity

        // 2. Create and save role
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        role.setDescription("Main user admin");
        role.setPermissions(Set.of(savedPermission)); // use managed entity
        Role savedRole = roleRepository.save(role);

        // 3. Create and save user
        PlatformUser newUser = new PlatformUser();
        newUser.setFirstName(platformUser.getFirstName());
        newUser.setLastName(platformUser.getLastName());
        newUser.setEmail(platformUser.getEmail());
        newUser.setPhoneNumber(platformUser.getPhoneNumber());
        newUser.setPasswordHash(passwordEncoder.encode(platformUser.getPasswordHash()));
        newUser.setUserType(UserType.PLATFORM.name());
        newUser.setRoleId(savedRole); // use managed role
        newUser.setStreetAddress(platformUser.getStreetAddress());
        newUser.setCity(platformUser.getCity());
        newUser.setState(platformUser.getState());
        newUser.setPostalCode(platformUser.getPostalCode());
        newUser.setCountry(platformUser.getCountry());

        return userRepository.save(newUser);
    }


    @Override
    public PlatformUser updateUser(PlatformUser platformUser) {
        return null;
    }

    @Override
    public Optional<PlatformUser> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<PlatformUser> findById(String email) {
        return Optional.empty();
    }

    @Override
    public List<PlatformUser> findAll() {
        return List.of();
    }

    @Override
    public Page<PlatformUser> findAllPageable() {
        return null;
    }
}
