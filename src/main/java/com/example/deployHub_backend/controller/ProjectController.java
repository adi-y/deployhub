package com.example.deployHub_backend.controller;

import com.example.deployHub_backend.dto.request.CreateProjectRequest;
import com.example.deployHub_backend.dto.response.ProjectResponse;
import com.example.deployHub_backend.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody CreateProjectRequest request){
        ProjectResponse response = projectService.createProject(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getMyProject(){
        List<ProjectResponse> response = projectService.getMyProjects();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{projectId}/clone")
    public ResponseEntity<String> cloneProject(@PathVariable Long projectId){
        projectService.cloneProject(projectId);
        return ResponseEntity.ok("Repository cloned successfully");
    }
}
