package com.example.deployHub_backend.service.deployment.root;

import javax.xml.xpath.XPath;
import java.nio.file.Path;

public interface ProjectRootResolver {
    Path resolve(Path resolvePath);
}
