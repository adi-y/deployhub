package com.example.deployHub_backend.service.deployment.build.strategy.impl;

import com.example.deployHub_backend.service.command.CommandExecutorService;
import com.example.deployHub_backend.service.deployment.build.strategy.BuildStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReactBuildStrategy implements BuildStrategy {
    private final CommandExecutorService commandExecutorService;

    @Override
    public boolean supports(Path projectPath){
        return Files.exists(projectPath.resolve("package.json"));
    }

    @Override
    public void build(Path projectPath){
        commandExecutorService.execute(
                projectPath,
                List.of("npm", "install")
        );

        commandExecutorService.execute(
                projectPath,
                List.of("npm","run","build")
        );
    }
}
