package com.shipping.saas.shippingSaas.domain;

import com.shipping.saas.shippingSaas.domain.dto.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "platform_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlatformUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String passwordHash;

    private String userType;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roleId;

    @Builder.Default
    private boolean isActive = true;

    private String streetAddress;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    @Builder.Default
    private Instant createdAt = Instant.now();
    @Builder.Default
    private Instant updatedAt = Instant.now();


}
