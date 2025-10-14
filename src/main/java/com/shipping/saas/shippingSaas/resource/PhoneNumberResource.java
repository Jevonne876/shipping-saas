package com.shipping.saas.shippingSaas.resource;


import com.shipping.saas.shippingSaas.domain.dto.PhoneNumberDTO;
import com.shipping.saas.shippingSaas.service.impl.PhoneNumberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/phone-number/")
@Slf4j
@RequiredArgsConstructor
public class PhoneNumberResource {

    private final PhoneNumberServiceImpl phoneNumberService;

    @PostMapping
    public ResponseEntity<PhoneNumberDTO> create(@RequestBody PhoneNumberDTO phoneNumber) {

        log.info("creating phone number {}", phoneNumber);

        return new ResponseEntity<>(phoneNumberService.createPhoneNumber(phoneNumber), CREATED);
    }

    @PutMapping
    public ResponseEntity<PhoneNumberDTO> update(@RequestBody PhoneNumberDTO phoneNumber) {
        log.info("updating phone number {}", phoneNumber);
        return new ResponseEntity<>(phoneNumberService.updatePhoneNumbers(phoneNumber), OK);
    }


}
