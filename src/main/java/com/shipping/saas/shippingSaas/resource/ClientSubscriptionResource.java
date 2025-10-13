package com.shipping.saas.shippingSaas.resource;

import com.shipping.saas.shippingSaas.domain.dto.ClientSubscriptionsDTO;
import com.shipping.saas.shippingSaas.service.impl.ClientSubscriptionServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/client-subscriptions/")
@RequiredArgsConstructor
@Slf4j
public class ClientSubscriptionResource {

    private final ClientSubscriptionServiceImpl clientSubscriptionServiceImpl;

    @PostMapping
    public ResponseEntity<ClientSubscriptionsDTO> createClientSubscription(@RequestBody ClientSubscriptionsDTO dto) {
        log.info("creating client subscription");
        return new ResponseEntity<>(clientSubscriptionServiceImpl.save(dto), CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClientSubscriptionsDTO>> getAll() {
        log.info("getting all client-subscriptions");
        return ResponseEntity.ok(clientSubscriptionServiceImpl.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ClientSubscriptionsDTO> getById(@PathVariable UUID id) {
        log.info("getting client subscription with id {}", id);
        return clientSubscriptionServiceImpl.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("client/{clientId}")
    public ResponseEntity<List<ClientSubscriptionsDTO>> getByClient(@PathVariable UUID clientId) {
        log.info("getting client subscription with client id {}", clientId);
        return ResponseEntity.ok(clientSubscriptionServiceImpl.findByClientId(clientId));
    }

    @PutMapping("{id}/deactivate")
    public ResponseEntity<ClientSubscriptionsDTO> deactivate(@PathVariable UUID id) {
        log.info("deactivating client subscription with id {}", id);
        return ResponseEntity.ok(clientSubscriptionServiceImpl.deactivate(id));
    }
}
