package com.shipping.saas.shippingSaas.domain;

import com.shipping.saas.shippingSaas.domain.clients.Client;
import com.shipping.saas.shippingSaas.domain.clients.ClientCustomer;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // 🔗 Each client has one warehouse address (or main hub)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false, unique = true)
    private Client client;

    // 🔗 Many client customers can share this warehouse
    @OneToMany(mappedBy = "warehouse")
    private List<ClientCustomer> clientCustomers;

    // 🏷 Label for display
    @Column(length = 50)
    private String label;

    // 🧱 Optional unique prefix for suite numbers or PO box identifiers
    @Column(name = "suite_prefix", length = 20, unique = true, nullable = false)
    private String suitePrefix;

    // 🌍 City reference (useful for region grouping)
    @Column(length = 100)
    private String city;

    @Builder.Default
    @Column(name = "is_active")
    private boolean isActive = true;

    @Builder.Default
    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "updated_by", nullable = false)
    private String updatedBy;
}
