package com.shipping.saas.shippingSaas.domain;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
    name = "client_customers",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"client_id", "customer_id"}),
        @UniqueConstraint(columnNames = {"suite_code"})
    })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class ClientCustomer {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customers customer;

    @Column(name = "suite_code", nullable = false, unique = true)
    private String suiteCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pickup_location_id")
    private PickUpLocation pickupLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_address_id")
    private WarehouseAddress warehouseAddress;

    @Column(name = "user_type", nullable = false)
    private String userType;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;


}
