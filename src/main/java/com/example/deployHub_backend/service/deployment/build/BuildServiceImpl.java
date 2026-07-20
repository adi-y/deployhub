package com.example.deployHub_backend.service.deployment.build;

import com.example.deployHub_backend.service.deployment.build.detector.ProjectTypeDetector;
import com.example.deployHub_backend.service.deployment.build.strategy.BuildStrategy;
import com.example.deployHub_backend.service.deployment.root.ProjectRootResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class BuildServiceImpl implements BuildService{
    private final ProjectTypeDetector projectTypeDetector;
    private final ProjectRootResolver projectRootResolver;

    @Override
    public void build(Path projectPath){
        Path projectRoot = projectRootResolver.resolve(projectPath);

        BuildStrategy strategy = projectTypeDetector.detect(projectRoot);
        strategy.build(projectRoot);
    }
}
