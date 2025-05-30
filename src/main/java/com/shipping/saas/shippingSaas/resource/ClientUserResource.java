package com.shipping.saas.shippingSaas.resource;

import com.shipping.saas.shippingSaas.domain.ClientUser;
import com.shipping.saas.shippingSaas.domain.dto.ClientUserDTO;
import com.shipping.saas.shippingSaas.service.impl.ClientUserService;
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

    private final ClientUserService clientUserService;


    @PostMapping("")
    public ResponseEntity<ClientUser> create(@RequestBody ClientUserDTO clientUser) throws Exception {

        return new ResponseEntity<>(clientUserService.create(clientUser), CREATED);
    }
}
