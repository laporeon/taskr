package com.laporeon.taskr.helpers;

import com.laporeon.taskr.entities.Task;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileStorageHandler {

    private static final Path FILE_PATH = Paths.get("files", "tasks.txt");

    public static void saveTaskToFile(Task task) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(FILE_PATH, StandardOpenOption.APPEND)) {
            String taskToCsvString = task.toCsvString();
            bufferedWriter.write(taskToCsvString);
        } catch (IOException exception) {
            if (exception instanceof NoSuchFileException) {
                throw new RuntimeException("Could not save task. File \"" + FILE_PATH + "\" does not exist.");
            }
            throw new RuntimeException("An unexpected error occurred" + exception.getMessage(), exception);
        }
    }

}