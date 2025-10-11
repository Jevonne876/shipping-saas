package com.shipping.saas.shippingSaas.service;

import com.shipping.saas.shippingSaas.domain.PhoneNumbers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PhoneNumbersService {

    PhoneNumbers createPhoneNumbers(PhoneNumbers phoneNumbers);

    PhoneNumbers updatePhoneNumbers(PhoneNumbers phoneNumbers);

    List<PhoneNumbers> findPhoneNumbersByOwnerId(UUID ownerId);

  Optional<PhoneNumbers> findById(UUID phoneNumbersId);

}
