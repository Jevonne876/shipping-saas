package com.shipping.saas.shippingSaas.util;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class TimeZoneService {

    /**
     * Convert UTC Instant → client's local ZonedDateTime.
     *
     * @param utcInstant     timestamp from DB (stored as UTC)
     * @param clientTimeZone e.g. "America/Jamaica"
     */
    public ZonedDateTime toClientTime(Instant utcInstant, String clientTimeZone) {
        if (utcInstant == null) return null;
        ZoneId zone = ZoneId.of(clientTimeZone != null ? clientTimeZone : "UTC");
        return utcInstant.atZone(zone);
    }

    /**
     * Convert client local ZonedDateTime → UTC Instant (for storing in DB).
     *
     * @param clientDateTime local date/time from the client side
     * @param clientTimeZone e.g. "America/Jamaica"
     */
    public Instant toUtc(ZonedDateTime clientDateTime, String clientTimeZone) {
        if (clientDateTime == null) return null;
        ZoneId zone = ZoneId.of(clientTimeZone != null ? clientTimeZone : "UTC");
        return clientDateTime.withZoneSameInstant(ZoneId.of("UTC")).toInstant();
    }

    /**
     * Utility to get the current Instant in UTC.
     */
    public Instant nowUtc() {
        return Instant.now();
    }

    /**
     * Utility to get current time in client's local zone.
     */
    public ZonedDateTime nowClient(String clientTimeZone) {
        ZoneId zone = ZoneId.of(clientTimeZone != null ? clientTimeZone : "UTC");
        return ZonedDateTime.now(zone);
    }
}
