package com.shipping.saas.shippingSaas.service.impl;


import com.shipping.saas.shippingSaas.domain.ClientUser;
import com.shipping.saas.shippingSaas.domain.PlatformUser;
import com.shipping.saas.shippingSaas.repository.ClientUserRepository;
import com.shipping.saas.shippingSaas.repository.PlatformUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatformUserService implements UserDetailsService {

    private final PlatformUserRepository platformUserRepository;

    private final ClientUserRepository clientUserRepository;

    public PlatformUserService(PlatformUserRepository platformUserRepository, ClientUserRepository clientUserRepository) {
        this.platformUserRepository = platformUserRepository;
        this.clientUserRepository = clientUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        PlatformUser user = platformUserRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(
            user.getEmail(),
            user.getPasswordHash(),
            List.of(new SimpleGrantedAuthority(user.getRoleId().getName())));
    }

    public UserDetails loadByUsername(String username) throws UsernameNotFoundException {

        ClientUser clientUser = clientUserRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(
            clientUser.getEmail(),
            clientUser.getPasswordHash(),
            List.of(new SimpleGrantedAuthority(clientUser.getRoldId().getName())));
    }


}
