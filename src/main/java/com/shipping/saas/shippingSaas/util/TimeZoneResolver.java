package com.shipping.saas.shippingSaas.util;


import com.shipping.saas.shippingSaas.jwt.JwtUtil;
import com.shipping.saas.shippingSaas.repository.ClientRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TimeZoneResolver {

    private final ClientRepository clientRepository;
    private final JwtUtil jwtUtil;

    /**
     * Resolve the current client's time zone.
     * Priority:
     * 1️⃣ Explicit header "X-Client-TimeZone"
     * 2️⃣ Authenticated client's record
     * 3️⃣ Default "UTC"
     */
    public String resolveClientTimeZone(HttpServletRequest request) {
        // 1️⃣ Check explicit header (frontend can send it if known)
        String headerZone = request.getHeader("X-Client-TimeZone");
        if (headerZone != null && !headerZone.isBlank()) {
            return headerZone;
        }

        // 2️⃣ Extract from JWT
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                String clientCode = jwtUtil.extractClientCode(token);
                if (clientCode != null) {
                    return clientRepository.findByCompanyCode(clientCode)
                        .map(client -> client.getTimeZone())
                        .orElse("UTC");
                }
            } catch (Exception ignored) {}
        }

        // 3️⃣ Fallback
        return "UTC";
    }
}
