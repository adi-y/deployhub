package com.example.deployHub_backend.service;

import com.example.deployHub_backend.entity.Project;

public interface GitService {
    void cloneRepository(Project project);
}
