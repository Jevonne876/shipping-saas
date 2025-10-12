package com.shipping.saas.shippingSaas.resource;


import com.shipping.saas.shippingSaas.domain.PhoneNumbers;
import com.shipping.saas.shippingSaas.service.impl.PhoneNumberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/phone-number/")
@Slf4j
@RequiredArgsConstructor
public class PhoneNumberResource {

    private final PhoneNumberServiceImpl phoneNumberService;

    @PostMapping
    public ResponseEntity<PhoneNumbers> create(@RequestBody PhoneNumbers phoneNumbers) {

        log.info("creating phone number {}", phoneNumbers);

        return new ResponseEntity<>(phoneNumberService.createPhoneNumbers(phoneNumbers), CREATED);
    }

    @PutMapping
    public ResponseEntity<PhoneNumbers> update(@RequestBody PhoneNumbers phoneNumbers) {
        log.info("updating phone number {}", phoneNumbers);
        return new ResponseEntity<>(phoneNumberService.updatePhoneNumbers(phoneNumbers), OK);
    }


}
