package com.example.deployHub_backend.service.deployment.build;

import com.example.deployHub_backend.enums.ProjectType;

import java.nio.file.Path;

public interface BuildService {
    void build(Path projectPath);
}
