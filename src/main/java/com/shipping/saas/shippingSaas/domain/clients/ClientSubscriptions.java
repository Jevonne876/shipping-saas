package com.shipping.saas.shippingSaas.domain.clients;

import com.shipping.saas.shippingSaas.domain.enums.BillingCycle;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "client_subscriptions")
@Getter
@Setter
@ToString(exclude = {"client", "subscriptionPlan"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class ClientSubscriptions {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_plan_id", nullable = false)
    private SubscriptionPlans subscriptionPlan;

    @Column(name = "start_date", nullable = false)
    private Instant startDate = Instant.now();

    @Column(name = "end_date", nullable = true)
    private Instant endDate;

    @Builder.Default
    @Column(name = "is_active")
    private boolean isActive = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "billing_cycle")
    private BillingCycle billingCycle;

    @Column(name = "notes", nullable = true)
    private String notes;

    @Column(name = "price_at_signup")
    private BigDecimal priceAtSignup;

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


}
