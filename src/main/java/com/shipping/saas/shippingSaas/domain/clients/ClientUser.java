package com.shipping.saas.shippingSaas.domain.clients;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shipping.saas.shippingSaas.domain.Role;
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
@Table(name = "client_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class ClientUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;


    @Column(nullable = false)
    @JsonIgnore
    private String passwordHash;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roleId;

    @Builder.Default
    private boolean isActive = true;

    @CreatedDate
    private Instant createdAt;

    @CreatedBy
    private String createdBy;


    @LastModifiedDate
    private Instant updatedAt;

    @LastModifiedBy
    private String updatedBy;
}
