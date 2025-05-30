package com.shipping.saas.shippingSaas.resource;

import com.shipping.saas.shippingSaas.domain.Client;
import com.shipping.saas.shippingSaas.service.impl.ClientServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/clients")
@AllArgsConstructor
public class ClientResource {

    private  final ClientServiceImpl clientService;


    @PostMapping("")
    public ResponseEntity createClient(@RequestBody Client client) {

        return new ResponseEntity(clientService.create(client), CREATED);
    }

}
