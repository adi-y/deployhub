package com.example.deployHub_backend.service.deployment.build.detector;

import com.example.deployHub_backend.service.deployment.build.strategy.BuildStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProjectTypeDetector {

    private final List<BuildStrategy> buildStrategies;

    public BuildStrategy detect(Path projectPath){
        return buildStrategies.stream()
                .filter(buildStrategy -> buildStrategy.supports(projectPath))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unsupported project type"));
    }
}
