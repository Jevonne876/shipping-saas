package com.shipping.saas.shippingSaas.domain.clients;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String companyCode;

    @Column(unique = true, nullable = false)
    private String logoUrl;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ClientSubscriptions>  subscriptions;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ClientCustomer> clientCustomers;

    @Builder.Default
    private boolean isActive = true;

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "created_by", nullable = false)
    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "updated_by", nullable = false)
    @LastModifiedBy
    private String updatedBy;

    @Column(name = "time_zone", nullable = false)
    private String timeZone = "America/Jamaica";


    public void addSubscription(ClientSubscriptions subscription) {
        subscriptions.add(subscription);     // adds to the list on the Client side
        subscription.setClient(this);        // sets the reference on the Subscription side
    }


}
