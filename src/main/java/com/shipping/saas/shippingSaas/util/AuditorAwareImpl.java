package com.shipping.saas.shippingSaas.util;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditorAware")
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // 👇 You can integrate with JWT later
        // For now, return a system default
        return Optional.of("system");
    }
}
