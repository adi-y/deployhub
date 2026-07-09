package com.example.deployHub_backend.service.storage.impl;

import com.example.deployHub_backend.service.storage.ProjectPathService;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class ProjectPathServiceImpl implements ProjectPathService {

    @Override
    public Path getProjectPath(Long projectId){
        return Path.of(
                "deployhub_storage",
                "repositories",
                projectId.toString()
        );
    }
}
