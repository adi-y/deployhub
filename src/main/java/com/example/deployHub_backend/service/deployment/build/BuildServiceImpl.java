package com.example.deployHub_backend.service.deployment.build;

import com.example.deployHub_backend.service.deployment.build.detector.ProjectTypeDetector;
import com.example.deployHub_backend.service.deployment.build.strategy.BuildStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class BuildServiceImpl implements BuildService{
    private final ProjectTypeDetector projectTypeDetector;

    @Override
    public void build(Path projectPath){
        BuildStrategy strategy = projectTypeDetector.detect(projectPath);
        strategy.build(projectPath);
    }
}
