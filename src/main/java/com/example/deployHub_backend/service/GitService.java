package com.example.deployHub_backend.service;

import com.example.deployHub_backend.entity.Project;

import java.nio.file.Path;

public interface GitService {
    Path cloneRepository(Project project);
}
