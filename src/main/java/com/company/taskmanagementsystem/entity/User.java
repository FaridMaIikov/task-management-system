package com.company.taskmanagementsystem.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "first_name",nullable = false)
    String firstName;

    @Column(name = "last_name",nullable = false)
    String lastName;

    @Column(name = "phone")
    String phone;

    @Column(name = "email", nullable = false,unique = true)
    String email;

    @Column(name = "username", unique = true,nullable = false)
    String username;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "is_active", columnDefinition = "boolean default false")
    boolean isActive;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamp default now()")
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

}
