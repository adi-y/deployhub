package com.example.deployHub_backend.service.impl;

import com.example.deployHub_backend.enums.ProjectType;
import com.example.deployHub_backend.service.DetectionService;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class DetectionServiceImpl implements DetectionService {
    @Override
    public ProjectType detect(Path projectPath){
        if(Files.exists(projectPath.resolve("package.json"))){
            return ProjectType.REACT;
        }
        if(Files.exists(projectPath.resolve("pom.xml"))){
            return ProjectType.SPRING_BOOT;
        }
        if(Files.exists(projectPath.resolve("build.gradle"))){
            return ProjectType.GRADLE;
        }
        if(Files.exists(projectPath.resolve("requirements.txt"))){
            return ProjectType.PYTHON;
        }
        return ProjectType.UNKNOWN;

    }
}
