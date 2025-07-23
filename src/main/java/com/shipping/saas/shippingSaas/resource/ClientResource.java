package com.shipping.saas.shippingSaas.resource;

import com.shipping.saas.shippingSaas.domain.Client;
import com.shipping.saas.shippingSaas.service.impl.ClientServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * The type Client resource.
 */
@RestController
@RequestMapping("/api/clients")
@AllArgsConstructor
public class ClientResource {

    private final ClientServiceImpl clientService;


    /**
     * Create client response entity.
     *
     * @param client the client
     * @return the response entity
     */
    @PostMapping("")
    public ResponseEntity createClient(@RequestBody Client client) {

        return new ResponseEntity(clientService.create(client), CREATED);
    }


    /**
     * Gets all clients.
     *
     * @return the all clients
     */
    @GetMapping("")
    public ResponseEntity<List<Client>> getAllClients() {

        return new ResponseEntity<>(clientService.findAll(), OK);
    }


}
