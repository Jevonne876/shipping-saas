package com.shipping.saas.shippingSaas.domain.clients;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "subscription_plans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class SubscriptionPlans {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @OneToMany(mappedBy = "subscriptionPlan", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<ClientSubscriptions> clientSubscriptions;

    @Column(name = "monthly_price", nullable = false)
    private BigDecimal monthlyPrice;

    @Column(name = "annual_price")
    private BigDecimal annualPrice;

    @Column(name = "is_active")
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

    // ✅ Optional helper for consistency
    public void addClientSubscription(ClientSubscriptions subscription) {
        clientSubscriptions.add(subscription);
        subscription.setSubscriptionPlan(this);
    }

    public void removeClientSubscription(ClientSubscriptions subscription) {
        clientSubscriptions.remove(subscription);
        subscription.setSubscriptionPlan(null);
    }


}
