package com.example.deployHub_backend.service.command;

import java.nio.file.Path;
import java.util.List;

public interface CommandExecutorService {
    void execute(Path workingDirectory, List<String> command);
}
