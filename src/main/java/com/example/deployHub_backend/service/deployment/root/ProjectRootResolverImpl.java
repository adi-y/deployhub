package com.example.deployHub_backend.service.deployment.root;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectRootResolverImpl implements ProjectRootResolver {

        private final ObjectMapper objectMapper;

        private static final Set<String> PROJECT_FILES = Set.of(
                "package.json"
        );

        private static final String SCRIPTS = "scripts";
        private static final String BUILD = "build";

        @Override
        public Path resolve(Path repositoryPath){;

            try (Stream<Path> paths = Files.walk(repositoryPath)){
                    return paths
                            .filter(Files::isRegularFile)
                            .filter(path -> PROJECT_FILES.contains(path.getFileName().toString()))
                            .filter(this::isDeployableProject)
                            .map(Path::getParent)
                            .findFirst()
                            .orElseThrow(()-> new RuntimeException("No deployable project with a valid build script was found."));
            }

            catch (IOException e){
                throw new RuntimeException(e);
            }
        }

    private boolean isDeployableProject(Path packageJsonPath){

            try{
                JsonNode root = objectMapper.readTree(packageJsonPath.toFile());

                if(!root.has(SCRIPTS)){
                    return false;
                }
                JsonNode scripts = root.get(SCRIPTS);
                return scripts.has(BUILD);

            }catch (JacksonException e){
                return false;
            }
    }
}
