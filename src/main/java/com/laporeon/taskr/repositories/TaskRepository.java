package com.laporeon.taskr.repositories;

import com.laporeon.taskr.entities.Task;
import com.laporeon.taskr.enums.TaskPriority;
import com.laporeon.taskr.enums.TaskStatus;
import com.laporeon.taskr.helpers.FileStorageHandler;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class TaskRepository {

    private static final String INVALID_INDEX_MESSAGE = "Invalid or missing task index: %d. Please try again...";

    public void createTask(String title, TaskStatus status, TaskPriority priority) {
        Task task = Task.builder()
                .id(UUID.randomUUID())
                .title(title)
                .status(status)
                .priority(priority)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        FileStorageHandler.saveToFile(task);
    }

    public List<Task> listTasks() {
        return FileStorageHandler.readFile();
    }

    public void updateTask(int index, String title, TaskStatus status, TaskPriority priority) {
        List<Task> tasks = FileStorageHandler.readFile();

        Task task = getTaskByIndex(tasks, index);
        task.update(title, status, priority);

        FileStorageHandler.overwriteFileContent(tasks);
    }

    public void deleteTask(int index) {
        List<Task> tasks = FileStorageHandler.readFile();

        Task task = getTaskByIndex(tasks, index);
        tasks.remove(task);

        FileStorageHandler.overwriteFileContent(tasks);
    }

    private Task getTaskByIndex(List<Task> tasks, int index) {
        int size = tasks.size();

        if (index < 1 || index > size) {
            throw new IllegalArgumentException(String.format(INVALID_INDEX_MESSAGE, index));
        }

        return tasks.get(index - 1);
    }
}
