package com.example.deployHub_backend.repo;

import com.example.deployHub_backend.entity.Project;
import com.example.deployHub_backend.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepo extends JpaRepository<Project, Long> {
    List<Project> findByUser(Users user);

}
