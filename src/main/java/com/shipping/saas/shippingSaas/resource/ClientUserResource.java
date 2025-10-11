package com.shipping.saas.shippingSaas.resource;

import com.shipping.saas.shippingSaas.domain.clients.ClientUser;
import com.shipping.saas.shippingSaas.domain.dto.ClientUserDTO;
import com.shipping.saas.shippingSaas.service.impl.ClientUserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/client-users")
@AllArgsConstructor
public class ClientUserResource {

    private final ClientUserServiceImpl clientUserServiceImpl;


    @PostMapping("")
    public ResponseEntity<ClientUser> create(@RequestBody ClientUserDTO clientUser) throws Exception {

        return new ResponseEntity<>(clientUserServiceImpl.create(clientUser), CREATED);
    }
}
