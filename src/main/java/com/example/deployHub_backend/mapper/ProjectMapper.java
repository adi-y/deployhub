package com.example.deployHub_backend.mapper;

import com.example.deployHub_backend.dto.response.ProjectResponse;
import com.example.deployHub_backend.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    public ProjectResponse toResponse(Project projects){
        return ProjectResponse.builder()
                .id(projects.getId())
                .name(projects.getName())
                .githubUrl(projects.getGitHubUrl())
                .status(projects.getStatus())
                .build();
    }
}
