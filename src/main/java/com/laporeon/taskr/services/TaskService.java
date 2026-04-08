package com.laporeon.taskr.services;

import com.laporeon.taskr.entities.Task;
import com.laporeon.taskr.enums.TaskPriority;
import com.laporeon.taskr.enums.TaskStatus;
import com.laporeon.taskr.exceptions.InvalidArgumentException;
import com.laporeon.taskr.repositories.TaskRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void createTask(String title, TaskStatus status, TaskPriority priority) {
        Task task = Task.builder()
                        .id(UUID.randomUUID())
                        .title(title)
                        .status(status)
                        .priority(priority)
                        .createdAt(Instant.now())
                        .updatedAt(Instant.now())
                        .build();

        taskRepository.save(task);
    }

    public List<Task> listTasks() {
        return taskRepository.listTasks();
    }

    public void updateTask(int index, String title, TaskStatus status, TaskPriority priority) {
        boolean hasTitle = title != null && !title.isBlank();
        boolean hasStatus = status != null;
        boolean hasPriority = priority != null;

        if (!hasTitle && !hasStatus && !hasPriority) {
            throw new InvalidArgumentException("Provide at least one field to update: title, status, or priority.");
        }

        validateIndex(index);

        taskRepository.update(index, title, status, priority);

    }

    public void deleteTask(int index) {
        validateIndex(index);
        taskRepository.delete(index);
    }

    private void validateIndex(int index) {
        int size = taskRepository.listTasks().size();

        if (index < 1 || index > size) {
            throw new InvalidArgumentException("Invalid task index: " + index + ". Please try again...");
        }

    }

}
