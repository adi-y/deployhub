package com.example.deployHub_backend.entity;

import com.example.deployHub_backend.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;

    private String gitHubUrl;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;
}
