package com.shipping.saas.shippingSaas.service;

import com.shipping.saas.shippingSaas.domain.ClientUser;
import com.shipping.saas.shippingSaas.domain.dto.ClientUserDTO;

import java.util.List;


public interface ClientUserService {


    ClientUser create(ClientUserDTO clientUser) throws Exception;
    ClientUser update(ClientUser clientUser);
    ClientUser delete(Long id);
    ClientUser findById(Long id);
    List<ClientUser> findAll();
    List<ClientUser> findByClientId(Long clientId);

}
