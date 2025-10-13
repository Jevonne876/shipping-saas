package com.shipping.saas.shippingSaas.service;

import com.shipping.saas.shippingSaas.domain.PhoneNumbers;
import com.shipping.saas.shippingSaas.domain.dto.PhoneNumberDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PhoneNumbersService {

    PhoneNumberDTO createPhoneNumbers(PhoneNumberDTO dto);

    PhoneNumberDTO updatePhoneNumbers(PhoneNumberDTO dto);

    List<PhoneNumberDTO> findPhoneNumbersByOwnerId(UUID ownerId);

  Optional<PhoneNumberDTO> findById(UUID phoneNumbersId);

}
