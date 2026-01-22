package com.company.domain.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false)
    public String fullName;

    @Column(nullable = false, unique = true)
    public String email;

    public Instant createdAt = Instant.now();
}
