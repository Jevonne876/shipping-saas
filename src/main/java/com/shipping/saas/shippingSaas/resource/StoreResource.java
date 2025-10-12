package com.shipping.saas.shippingSaas.resource;

import com.shipping.saas.shippingSaas.domain.Store;
import com.shipping.saas.shippingSaas.service.impl.StoreServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store/")
public class StoreResource {

    private final StoreServiceImpl storeService;


    @PostMapping
    public ResponseEntity<Store> save(@RequestBody Store store) {

        log.info("saving store for client {}", store);

        return new ResponseEntity<>(storeService.create(store), OK);
    }

    @PutMapping("update")
    public ResponseEntity<Store> update(@RequestBody Store store) {
        log.info("updating store for client {}", store);
        return new ResponseEntity<>(storeService.update(store), OK);
    }

    @GetMapping
    public ResponseEntity<List<Store>> findAll() {
        log.info("find all stores");
        return new ResponseEntity<>(storeService.findAll(), OK);
    }

    @GetMapping("client/{client}")
    public ResponseEntity<List<Store>> findByClientId(@PathVariable UUID clientId) {
        log.info("find stores for client {}", clientId);
        return new ResponseEntity<>(storeService.findByClientId(clientId), OK);
    }


}
