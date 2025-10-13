package com.shipping.saas.shippingSaas.service.impl;

import com.shipping.saas.shippingSaas.domain.PhoneNumbers;
import com.shipping.saas.shippingSaas.domain.dto.PhoneNumberDTO;
import com.shipping.saas.shippingSaas.repository.PhoneNumberRepository;
import com.shipping.saas.shippingSaas.service.PhoneNumbersService;
import com.shipping.saas.shippingSaas.service.mapper.PhoneNumberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@Repository
@RequiredArgsConstructor
@Transactional
public class PhoneNumberServiceImpl implements PhoneNumbersService {

    private final PhoneNumberRepository phoneNumberRepository;
    private final PhoneNumberMapper phoneNumberMapper;

    @Override
    public PhoneNumberDTO createPhoneNumbers(PhoneNumberDTO phoneNumbers) {

        log.info("creating phone numbers for {} type {}", phoneNumbers.getOwnerId(), phoneNumbers.getPhoneType());

        PhoneNumbers phoneNumber = phoneNumberMapper.toEntity(phoneNumbers);

        return phoneNumberMapper.toDto(phoneNumberRepository.save(phoneNumber));
    }

    @Override
    public PhoneNumberDTO updatePhoneNumbers(PhoneNumberDTO phoneNumbers) {

        log.info("updating phone number for {} type {}", phoneNumbers.getOwnerId(), phoneNumbers.getPhoneType());

        PhoneNumbers update = phoneNumberRepository.findById(phoneNumbers.getId()).orElseThrow(() -> new IllegalArgumentException("phone number not found" + phoneNumbers.getPhoneNumber()));
        update.setPhoneType(phoneNumbers.getPhoneType());
        update.setPhoneNumber(phoneNumbers.getPhoneNumber());

        return phoneNumberMapper.toDto(phoneNumberRepository.save(update));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PhoneNumberDTO> findById(UUID phoneNumbersId) {
        log.info("finding phone number for {} type {}", phoneNumbersId, phoneNumbersId);


        return Optional.of(phoneNumberMapper
            .toDto(phoneNumberRepository
                .findById(phoneNumbersId)
                .orElseThrow(() -> new IllegalArgumentException("phone number not found" + phoneNumbersId))));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PhoneNumberDTO> findPhoneNumbersByOwnerId(UUID ownerId) {

        log.info("finding phone numbers for {}", ownerId);

        return phoneNumberRepository.findByOwnerId(ownerId)
            .stream()
            .map(phoneNumberMapper::toDto)
            .toList();
    }
}
