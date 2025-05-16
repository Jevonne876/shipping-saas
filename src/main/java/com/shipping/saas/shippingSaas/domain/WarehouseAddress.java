package com.shipping.saas.shippingSaas.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "warehouse_addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseAddress {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private String label;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String postalCode;

    @Column(unique = true)
    private String suitePrefix;

    @Builder.Default
    private String country = "USA";

    @Builder.Default
    private boolean isActive = true;

    @Builder.Default
    private Instant createdAt = Instant.now();
}
