package com.example.deployHub_backend.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProjectRequest {
    private String name;
    private String githubUrl;
}
