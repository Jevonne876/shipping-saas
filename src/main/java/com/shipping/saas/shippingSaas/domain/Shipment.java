package com.shipping.saas.shippingSaas.domain;

import com.shipping.saas.shippingSaas.domain.clients.Client;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "shipments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    // 🔗 Link to packages
    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
    private List<Package> packages;

    @Column(nullable = false, unique = true)
    private String shipmentNumber;

    private String flightNumber;
    private String vesselName;
    private String originCity;
    private String destinationCity;
    private Instant departureDate;
    private Instant arrivalDate;

    private String currentStatus = "CREATED";
    private BigDecimal totalWeightKg;
    private int totalPackages;
    private String remarks;

    @CreatedDate
    private Instant createdAt;

    private String createdBy;

    @LastModifiedDate
    private Instant updatedAt;

    private String updatedBy;

    @Builder.Default
    private boolean isActive = true;


}
