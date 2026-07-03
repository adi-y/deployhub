package com.example.deployHub_backend.service;

import com.example.deployHub_backend.dto.request.CreateProjectRequest;
import com.example.deployHub_backend.dto.response.ProjectResponse;

import java.util.List;

public interface ProjectService {
    ProjectResponse createProject(CreateProjectRequest request);

    List<ProjectResponse> getMyProjects();


}
