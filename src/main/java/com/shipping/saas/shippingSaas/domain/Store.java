package com.shipping.saas.shippingSaas.domain;

import com.shipping.saas.shippingSaas.domain.clients.Client;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "stores", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"client_id", "code"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code; // e.g. "KNG", "MBJ"

    @Builder.Default
    private boolean isActive = true;

    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
}
