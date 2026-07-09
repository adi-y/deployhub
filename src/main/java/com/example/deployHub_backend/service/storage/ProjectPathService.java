package com.example.deployHub_backend.service.storage;

import java.nio.file.Path;

public interface ProjectPathService {
    Path getProjectPath(Long projectId);
}
