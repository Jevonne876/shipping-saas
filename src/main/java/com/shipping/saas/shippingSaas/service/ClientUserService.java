package com.shipping.saas.shippingSaas.service;

import com.shipping.saas.shippingSaas.domain.ClientUser;
import com.shipping.saas.shippingSaas.repository.ClientUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientUserService implements UserDetailsService {

    private final ClientUserRepository clientUserRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ClientUser user = clientUserRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));


        return new User(
            user.getEmail(),
            user.getPasswordHash(),
            List.of(new SimpleGrantedAuthority(user.getRoleId().getName()))
        );
    }
}
