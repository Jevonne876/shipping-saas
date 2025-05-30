package com.shipping.saas.shippingSaas.service.impl;

import com.shipping.saas.shippingSaas.domain.Client;
import com.shipping.saas.shippingSaas.domain.ClientUser;
import com.shipping.saas.shippingSaas.domain.CustomUserDetails;
import com.shipping.saas.shippingSaas.domain.Role;
import com.shipping.saas.shippingSaas.domain.dto.ClientUserDTO;
import com.shipping.saas.shippingSaas.domain.dto.UserType;
import com.shipping.saas.shippingSaas.repository.ClientRepository;
import com.shipping.saas.shippingSaas.repository.ClientUserRepository;
import com.shipping.saas.shippingSaas.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientUserServiceImpl implements UserDetailsService, com.shipping.saas.shippingSaas.service.ClientUserService {

    private final ClientUserRepository clientUserRepository;

    private final RoleRepository roleRepository;

    private final ClientRepository clientRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ClientUser user = clientUserRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));

        return new CustomUserDetails(
            user.getEmail(),
            user.getPasswordHash(),
            List.of(new SimpleGrantedAuthority(user.getRoleId().getName())),
            user.getUserType() // Or .name() if it's an enum
        );
    }

    @Override
    public ClientUser create(ClientUserDTO clientUser) throws Exception {

        Role role = roleRepository.findByName(clientUser.getRole())
            .orElseThrow(() -> new Exception("Role not found."));


        Client client = clientRepository.findByName(clientUser.getClientName())
            .orElseThrow(() -> new Exception("Client not found."));


        ClientUser newUser = ClientUser.builder()
            .firstName(clientUser.getFirstName())
            .lastName(clientUser.getLastName())
            .email(clientUser.getEmail())
            .phoneNumber(clientUser.getPhoneNumber())
            .passwordHash(bCryptPasswordEncoder.encode(clientUser.getPasswordHash()))
            .userType(UserType.CLIENT_USER.name())
            .roleId(role)
            .client(client)
            .build();


        return clientUserRepository.save(newUser);
    }

    @Override
    public ClientUser update(ClientUser clientUser) {
        return null;
    }

    @Override
    public ClientUser delete(Long id) {
        return null;
    }

    @Override
    public ClientUser findById(Long id) {
        return null;
    }

    @Override
    public List<ClientUser> findAll() {
        return List.of();
    }

    @Override
    public List<ClientUser> findByClientId(Long clientId) {
        return List.of();
    }
}
