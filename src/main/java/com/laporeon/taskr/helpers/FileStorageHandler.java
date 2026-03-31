package com.laporeon.taskr.helpers;

import com.laporeon.taskr.entities.Task;
import com.laporeon.taskr.enums.TaskPriority;
import com.laporeon.taskr.enums.TaskStatus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileStorageHandler {

    private static final String FILE_HEADER = "ID;TITLE;STATUS;PRIORITY;CREATED_AT;UPDATED_AT\n";
    private static final Path FILE_PATH = Paths.get("files", "tasks.txt");

    public static void saveToFile(Task task) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(FILE_PATH, StandardOpenOption.APPEND)) {
            String taskToCsvString = task.toCsvString();
            bufferedWriter.write(taskToCsvString);
            bufferedWriter.newLine();
        } catch (IOException ex) {
            throw new RuntimeException("Error saving to file: " + ex.getMessage(), ex);
        }
    }

    public static List<Task> readFile() {
        List<Task> tasks = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(FILE_PATH)) {
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(";", -1);

                Task task = Task.builder()
                        .id(Integer.parseInt(attributes[0]))
                        .title(attributes[1])
                        .status(TaskStatus.fromString(attributes[2]))
                        .priority(TaskPriority.fromString(attributes[3]))
                        .build();

                tasks.add(task);
            }

            return tasks;
        } catch (IOException ex) {
            throw new RuntimeException("Error reading file: " + ex.getMessage(), ex);
        }
    }

    public static void overwriteFileContent(List<Task> tasks) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(FILE_PATH)) {
            bufferedWriter.write(FILE_HEADER);

            for (Task task : tasks) {
                String taskCsvString = task.toCsvString();
                bufferedWriter.write(taskCsvString);
                bufferedWriter.newLine();
            }

        } catch (IOException exception) {
            throw new RuntimeException("Error while trying to update file: " + exception.getMessage(), exception);
        }
    }
}