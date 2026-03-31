package com.laporeon.taskr.repositories;

import com.laporeon.taskr.entities.Task;
import com.laporeon.taskr.enums.TaskPriority;
import com.laporeon.taskr.enums.TaskStatus;
import com.laporeon.taskr.helpers.FileStorageHandler;

import java.util.List;

public class TaskRepository {

    private static final String INVALID_ID_MESSAGE = "Invalid or missing task ID: %d. Please try again...";

    public void createTask(String title, TaskStatus status, TaskPriority priority) {
        Task task = Task.builder()
                .id(getNextId())
                .title(title)
                .status(status)
                .priority(priority)
                .build();

        FileStorageHandler.saveToFile(task);
    }

    public List<Task> listTasks() {
        return FileStorageHandler.readFile();
    }

    public void deleteTask(int id) {
        List<Task> tasks = FileStorageHandler.readFile();

        boolean isDeleted = tasks.removeIf(task -> task.getId() == id);

        if (!isDeleted) {
            throw new IllegalArgumentException(String.format(INVALID_ID_MESSAGE, id));
        }

        FileStorageHandler.overwriteFileContent(tasks);
    }

    private Integer getNextId() {
        return listTasks().size() + 1;
    }

}
