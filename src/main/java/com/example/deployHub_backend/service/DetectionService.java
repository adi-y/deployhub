package com.example.deployHub_backend.service;

import com.example.deployHub_backend.enums.ProjectType;
import org.springframework.stereotype.Service;

import java.nio.file.Path;


public interface DetectionService {

    ProjectType detect(Path projectPath);
}
