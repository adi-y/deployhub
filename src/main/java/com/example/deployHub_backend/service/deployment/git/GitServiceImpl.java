package com.example.deployHub_backend.service.deployment.git;

import com.example.deployHub_backend.entity.Project;
import com.example.deployHub_backend.service.command.CommandExecutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GitServiceImpl implements GitService {
    private final CommandExecutorService commandExecutorService;

    @Override
    public Path cloneRepository(Project project) {


        Path destination = Path.of("deployhub_storage", "repositories", project.getId().toString());


        // Prevent cloning into an existing repository
        try{
            if(Files.exists(destination) && Files.list(destination).findAny().isPresent()){
                throw new RuntimeException("Repository already cloned.");
            }
            Files.createDirectories(destination.getParent());
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        System.out.println("Working Directory: " + System.getProperty("user.dir"));
        System.out.println("Destination: " + destination);
        System.out.println("GitHub URL: " + project.getGitHubUrl());

        Path workingDirectory = Path.of(System.getProperty("user.dir"));
        commandExecutorService.execute(
                workingDirectory,
                List.of(
                        "git",
                        "clone",
                        project.getGitHubUrl(),
                        destination.toString()
                )
        );


        return destination;
    }
}
