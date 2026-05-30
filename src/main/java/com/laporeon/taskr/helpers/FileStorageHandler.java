package com.laporeon.taskr.helpers;

import com.laporeon.taskr.entities.Task;
import com.laporeon.taskr.exceptions.TaskStorageException;

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

    private static final String FILE_HEADER = "ID;TITLE;STATUS;PRIORITY;CREATED_AT;UPDATED_AT";
    private static final Path FILE_PATH = Paths.get("files", "tasks.txt");
    private static final int TASK_ATTRIBUTES_SIZE = FILE_HEADER.split(";").length;
    private static final String INVALID_ATTRIBUTES_SIZE_MESSAGE = "Invalid file format. Expected %d columns but got %d instead";

    public static void saveToFile(Task task) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(FILE_PATH, StandardOpenOption.APPEND)) {
            String taskToCsvString = TaskSerializer.toCsvString(task);
            bufferedWriter.newLine();
            bufferedWriter.write(taskToCsvString);
        } catch (IOException ex) {
            throw new TaskStorageException("Error saving to file: " + ex.getMessage());
        }
    }

    public static List<Task> readFile() {
        List<Task> tasks = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(FILE_PATH)) {
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;

                String[] attributes = line.split(";", -1);

                if (attributes.length != TASK_ATTRIBUTES_SIZE) {
                    throw new TaskStorageException(INVALID_ATTRIBUTES_SIZE_MESSAGE.formatted(TASK_ATTRIBUTES_SIZE, attributes.length));
                }

                Task task = TaskSerializer.fromFields(attributes);
                tasks.add(task);
            }

            return tasks;
        } catch (IOException ex) {
            throw new TaskStorageException("Error reading file: " + ex.getMessage());
        }
    }

    public static void overwriteFileContent(List<Task> tasks) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(FILE_PATH)) {
            bufferedWriter.write(FILE_HEADER);
            bufferedWriter.newLine();

            for (Task task : tasks) {
                String taskCsvString = TaskSerializer.toCsvString(task);
                bufferedWriter.write(taskCsvString);
                bufferedWriter.newLine();
            }

        } catch (IOException exception) {
            throw new TaskStorageException("Error while trying to update file: " + exception.getMessage());
        }
    }

}