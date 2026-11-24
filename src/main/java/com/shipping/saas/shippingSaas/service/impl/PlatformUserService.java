package com.shipping.saas.shippingSaas.service.impl;


import com.shipping.saas.shippingSaas.domain.CustomUserDetails;
import com.shipping.saas.shippingSaas.domain.PlatformUser;
import com.shipping.saas.shippingSaas.repository.PlatformUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlatformUserService  {

    private final PlatformUserRepository platformUserRepository;


    public PlatformUser findByEmail(String email) {
        return platformUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
