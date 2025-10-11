package com.shipping.saas.shippingSaas.service.impl;

import com.shipping.saas.shippingSaas.domain.PhoneNumbers;
import com.shipping.saas.shippingSaas.repository.PhoneNumberRepository;
import com.shipping.saas.shippingSaas.service.PhoneNumbersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@Repository
@RequiredArgsConstructor
public class PhoneNumberServiceImpl implements PhoneNumbersService {

    private final PhoneNumberRepository phoneNumberRepository;

    @Override
    public PhoneNumbers createPhoneNumbers(PhoneNumbers phoneNumbers) {

        log.info("creating phone numbers for {} type {}", phoneNumbers.getOwnerId(), phoneNumbers.getPhoneType());

        PhoneNumbers newPhoneNumbers = PhoneNumbers.builder()
            .ownerId(phoneNumbers.getOwnerId())
            .phoneType(phoneNumbers.getPhoneType())
            .phoneNumber(phoneNumbers.getPhoneNumber())
            .build();

        return phoneNumberRepository.save(newPhoneNumbers);
    }

    @Override
    public PhoneNumbers updatePhoneNumbers(PhoneNumbers phoneNumbers) {

        log.info("updating phone number for {} type {}", phoneNumbers.getOwnerId(), phoneNumbers.getPhoneType());

        PhoneNumbers update = phoneNumberRepository.findById(phoneNumbers.getId()).orElseThrow(() -> new IllegalArgumentException("phone number not found" + phoneNumbers.getPhoneNumber()));
        update.setPhoneType(phoneNumbers.getPhoneType());
        update.setPhoneNumber(phoneNumbers.getPhoneNumber());

        return phoneNumberRepository.save(update);
    }

    @Override
    public Optional<PhoneNumbers> findById(UUID phoneNumbersId) {
        log.info("finding phone number for {} type {}", phoneNumbersId, phoneNumbersId);
        PhoneNumbers phoneNumbers = phoneNumberRepository.findById(phoneNumbersId)
            .orElseThrow(() -> new IllegalArgumentException("phone number not found" + phoneNumbersId));

        return Optional.ofNullable(phoneNumbers);
    }

    @Override
    public List<PhoneNumbers> findPhoneNumbersByOwnerId(UUID ownerId) {

        log.info("finding phone numbers for {}", ownerId);

        return phoneNumberRepository.findByOwnerId(ownerId);
    }
}
