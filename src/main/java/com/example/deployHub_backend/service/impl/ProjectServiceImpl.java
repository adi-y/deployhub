package com.example.deployHub_backend.service.impl;

import com.example.deployHub_backend.dto.request.CreateProjectRequest;
import com.example.deployHub_backend.dto.response.ProjectResponse;
import com.example.deployHub_backend.enums.ProjectStatus;
import com.example.deployHub_backend.entity.Project;
import com.example.deployHub_backend.entity.Users;
import com.example.deployHub_backend.enums.ProjectType;
import com.example.deployHub_backend.mapper.ProjectMapper;
import com.example.deployHub_backend.repo.ProjectRepo;
import com.example.deployHub_backend.service.CurrentUserDetailService;
import com.example.deployHub_backend.service.DetectionService;
import com.example.deployHub_backend.service.GitService;
import com.example.deployHub_backend.service.ProjectService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;



@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepo projectRepo;
    private final CurrentUserDetailService currentUserService;
    private final ProjectMapper projectMapper;
    private final GitService gitService;
    private final DetectionService detectionService;
    @Override
    public ProjectResponse  createProject(CreateProjectRequest request){


        Users user = currentUserService.getCurrentUser();

        Project projects = Project.builder()
                .name(request.getName())
                .gitHubUrl(request.getGithubUrl())
                .status(ProjectStatus.CREATED)
                .user(user)
                .build();

        Project savedProject = projectRepo.save(projects);

        return projectMapper.toResponse(savedProject);

    }

    @Override
    public List<ProjectResponse> getMyProjects(){
        Users user = currentUserService.getCurrentUser();

        return projectRepo.findByUser(user)
                .stream()
                .map(projectMapper::toResponse)
                .toList();
    }

    @Override
    public void cloneProject(Long projectId){
        Users currentUser = currentUserService.getCurrentUser();

        Project project = projectRepo.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));

        if(!project.getUser().getId().equals(currentUser.getId())){
            throw new RuntimeException("Access denied");
        }

        Path projectPath = gitService.cloneRepository(project);

        ProjectType projectType = detectionService.detect(projectPath);

        System.out.println("Detected Project Type: " + projectType);

    }

}
