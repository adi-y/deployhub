package com.example.deployHub_backend.service.deployment.git;

import com.example.deployHub_backend.entity.Project;

import java.nio.file.Path;

public interface GitService {
    Path cloneRepository(Project project);
}
