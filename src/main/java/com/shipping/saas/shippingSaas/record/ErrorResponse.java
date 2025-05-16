package com.shipping.saas.shippingSaas.record;

import java.time.Instant;

public record ErrorResponse(
    int status,
    String error,       // e.g. "Unauthorized", "Bad Request"
    String message,     // e.g. "Invalid username or password"
    String path,        // e.g. "/auth/login"
    Instant timestamp   // e.g. 2025-05-16T20:48:00Z
) {}
