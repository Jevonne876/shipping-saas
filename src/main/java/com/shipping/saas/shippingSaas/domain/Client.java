package com.shipping.saas.shippingSaas.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    @Column(unique = true, nullable = false)
    private String companyCode;
    @Column(unique = true, nullable = false)

    private String subscriptionPlan;

    @Column(unique = true, nullable = false)
    private String logoUrl;

    @Column(unique = true, nullable = false)
    private String streetAddress;

    @Column(unique = true, nullable = false)
    private String city;

    @Column(unique = true, nullable = false)
    private String state;

    @Column(unique = true, nullable = false)
    private String postalCode;

    @Column(unique = true, nullable = false)
    private String country;

    @Builder.Default
    private boolean isActive = true;

    @Builder.Default
    private Instant createdAt = Instant.now();

    @Builder.Default
    private Instant updatedAt = Instant.now();
}
