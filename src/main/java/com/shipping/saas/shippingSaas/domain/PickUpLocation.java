package com.shipping.saas.shippingSaas.domain;


import com.shipping.saas.shippingSaas.domain.clients.Client;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "pickup_locations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PickUpLocation {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private String name;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    @Builder.Default
    private boolean isActive = true;

    @Builder.Default
    private Instant createdAt = Instant.now();
}

