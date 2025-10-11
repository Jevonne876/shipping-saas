package com.shipping.saas.shippingSaas.resource;

import com.shipping.saas.shippingSaas.domain.Addresses;
import com.shipping.saas.shippingSaas.service.impl.AddressesServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("api/addresses/")
@RequiredArgsConstructor
@Slf4j
public class AddressesController {

    private final AddressesServiceImpl addressesService;

    @PostMapping
    public ResponseEntity<Addresses> saveAddress(@RequestBody Addresses addresses) {

        log.info("saving address {}", addresses);

        return new ResponseEntity<>(addressesService.createAddresses(addresses), CREATED);
    }

    @PostMapping("update")
    public ResponseEntity<Addresses> updateAddress(@RequestBody Addresses addresses) {
        log.info("updating address {}", addresses);
        return new ResponseEntity<>(addressesService.updateAddresses(addresses), OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Addresses>> findAddressById(@PathVariable UUID id) {
        log.info("finding address by id {}", id);

        return new ResponseEntity<>(addressesService.findById(id), OK);
    }



}
