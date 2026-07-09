package com.example.deployHub_backend.dto.response;

import com.example.deployHub_backend.enums.ProjectStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProjectResponse {
    private Long id;
    private String name;
    private String githubUrl;
    private ProjectStatus status;
}
