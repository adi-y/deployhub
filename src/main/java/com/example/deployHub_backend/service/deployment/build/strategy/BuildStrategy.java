package com.example.deployHub_backend.service.deployment.build.strategy;

import java.nio.file.Path;

public interface BuildStrategy {
    boolean supports(Path projectPath);
    void  build(Path projectPath);
}
