package com.shipping.saas.shippingSaas.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientCustomer {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    private String address;

    private String city;

    private String state;

    private String postalCode;

    private String country;

    @ManyToOne
    @JoinColumn(name = "pickup_location_id")
    private PickUpLocation pickupLocation;

    @ManyToOne
    @JoinColumn(name = "warehouse_address_id")
    private WarehouseAddress warehouseAddress;

    private String suiteCode; // e.g., unique ID assigned to customer for that warehouse

    @Column(nullable = false)
    private String userType;

    private boolean isActive = false;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
