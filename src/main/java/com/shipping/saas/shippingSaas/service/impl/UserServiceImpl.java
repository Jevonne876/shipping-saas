package com.shipping.saas.shippingSaas.service.impl;

import com.shipping.saas.shippingSaas.domain.PlatformUser;
import com.shipping.saas.shippingSaas.domain.Role;
import com.shipping.saas.shippingSaas.domain.dto.PlatformUserDTO;
import com.shipping.saas.shippingSaas.domain.dto.UserType;
import com.shipping.saas.shippingSaas.exceptions.BadRequestException;
import com.shipping.saas.shippingSaas.exceptions.DuplicateResourceException;
import com.shipping.saas.shippingSaas.exceptions.PlatformUserNotFoundException;
import com.shipping.saas.shippingSaas.repository.PlatformUserRepository;
import com.shipping.saas.shippingSaas.repository.RoleRepository;
import com.shipping.saas.shippingSaas.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The type User service.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final PlatformUserRepository platformUserRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final RoleRepository roleRepository;

    @Override
    public PlatformUser save(PlatformUserDTO newUser) throws Exception, BadRequestException {

        log.debug("save platform user");

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
    public List<PlatformUser> findAll() {
        log.debug("find all platform users");
        return platformUserRepository.findAll();
    }

    @Override
    public PlatformUser update(UUID id, PlatformUserDTO newUserData) throws Exception, BadRequestException {
        log.debug("update platform user");

        PlatformUser platformUser = platformUserRepository.findById(id).orElseThrow(() -> new PlatformUserNotFoundException("User not found."));

        // ✅ Check if another user has the same email
        if (platformUserRepository.existsByEmailAndIdNot(newUserData.getEmail(), id)) {
            throw new DuplicateResourceException("Email is already in use by another user.");
        }

        // ✅ Check if another user has the same phone number
        if (platformUserRepository.existsByPhoneNumberAndIdNot(newUserData.getPhoneNumber(), id)) {
            throw new DuplicateResourceException("Phone number is already in use by another user.");
        }

        platformUser.setFirstName(newUserData.getFirstName());
        platformUser.setLastName(newUserData.getLastName());
        platformUser.setEmail(newUserData.getEmail());
        platformUser.setPhoneNumber(newUserData.getPhoneNumber());
        platformUser.setStreetAddress(newUserData.getStreetAddress());
        platformUser.setCity(newUserData.getCity());
        platformUser.setState(newUserData.getState());
        platformUser.setPostalCode(newUserData.getPostalCode());
        platformUser.setCountry(newUserData.getCountry());
        return platformUserRepository.save(platformUser);
    }

    @Override
    public Optional<PlatformUser> findById(UUID id) {
        log.debug("find platform user by id {}", id);
        return Optional.ofNullable(platformUserRepository.findById(id).orElseThrow(() -> new PlatformUserNotFoundException("User Not Found")));
    }

    @Override
    public Optional<PlatformUser> findByEmail(String email) {
        log.debug("find platform user by email {}", email);
        return Optional.ofNullable(platformUserRepository.findByEmail(email).orElseThrow(() -> new PlatformUserNotFoundException("User Not Found")));
    }

    @Override
    public Optional<PlatformUser> findByUsername(String userName) {
        log.debug("find platform user by username {}", userName);
        return Optional.ofNullable(platformUserRepository.findByEmail(userName).orElseThrow(() -> new PlatformUserNotFoundException("User Not Found")));
    }



}
