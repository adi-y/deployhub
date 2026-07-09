package com.example.deployHub_backend.service.command;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.List;

@Service
public class CommandExecutorServiceImpl implements CommandExecutorService {

    @Override
    public void execute(Path workingDirectory, List<String> command){
        ProcessBuilder processBuilder = new ProcessBuilder(command);

        processBuilder.directory(workingDirectory.toFile());
        processBuilder.redirectErrorStream(true);

        try{
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while((line = reader.readLine()) != null){
                System.out.println(line);
            }

            int exitCode = process.waitFor();

            if(exitCode !=0){
                throw new RuntimeException("Command failed with exit code "+ exitCode);
            }
        }catch (IOException | InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
