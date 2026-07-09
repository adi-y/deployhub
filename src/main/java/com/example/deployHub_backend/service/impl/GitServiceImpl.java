package com.example.deployHub_backend.service.impl;

import com.example.deployHub_backend.entity.Project;
import com.example.deployHub_backend.service.GitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class GitServiceImpl implements GitService {

    @Override
    public Path cloneRepository(Project project) {

        String destination = "deployhub_storage/repositories/" + project.getId();

        File folder = new File(destination);




        // Prevent cloning into an existing repository
        if (folder.exists() && folder.list() != null && folder.list().length > 0) {
            throw new RuntimeException("Repository already cloned.");
        }

        // Create parent folders if they don't exist
        folder.mkdirs();

        System.out.println("Working Directory: " + System.getProperty("user.dir"));
        System.out.println("Destination: " + destination);
        System.out.println("GitHub URL: " + project.getGitHubUrl());

        ProcessBuilder processBuilder = new ProcessBuilder(
                "git",
                "clone",
                project.getGitHubUrl(),
                destination
        );

        processBuilder.redirectErrorStream(true);

        try {

            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            StringBuilder output = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();

            System.out.println("Exit Code = " + exitCode);

            if (exitCode != 0) {
                throw new RuntimeException(
                        "Git clone failed.\n\n" + output
                );
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return Path.of(destination);
    }
}
