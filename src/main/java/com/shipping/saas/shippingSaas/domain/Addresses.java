package com.shipping.saas.shippingSaas.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Addresses {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "addressable_id", nullable = false)
    private UUID addressableId;

    @Column(name = "addressable_type", nullable = false)
    private String addressableType;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "state_or_parish", nullable = false)
    private String stateOrParish;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

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
