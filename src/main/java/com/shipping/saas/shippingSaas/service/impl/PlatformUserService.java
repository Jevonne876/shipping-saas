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
public class PlatformUserService implements UserDetailsService {

    private final PlatformUserRepository platformUserRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        PlatformUser user = platformUserRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        return new CustomUserDetails(
            user.getEmail(),
            user.getPasswordHash(),
            List.of(new SimpleGrantedAuthority(user.getRoleId().getName())),
            user.getUserType() // Or .name() if it's an enum
        );
    }

    public PlatformUser findByEmail(String email) {
        return platformUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
