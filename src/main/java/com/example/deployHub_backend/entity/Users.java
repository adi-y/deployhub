package com.example.deployHub_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;
}
