package com.laporeon.taskr.services;

import com.laporeon.taskr.entities.Task;
import com.laporeon.taskr.enums.TaskPriority;
import com.laporeon.taskr.enums.TaskStatus;
import com.laporeon.taskr.exceptions.InvalidArgumentException;
import com.laporeon.taskr.helpers.TaskSerializer;
import com.laporeon.taskr.repositories.TaskRepository;

import java.util.List;

public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void createTask(String title, TaskStatus status, TaskPriority priority) {
        validateTitle(title);

        Task task = TaskSerializer.toEntity(title, status, priority);
        taskRepository.save(task);
    }

    public List<Task> listTasks(TaskStatus status) {

        if (status == null) {
            return taskRepository.listTasks();
        }

        return taskRepository.listTasksByStatus(status);
    }

    public void updateTask(int index, String title, TaskStatus status, TaskPriority priority) {
        validateIndex(index);
        validateUpdateOptions(title, status, priority);
        if (title != null) validateTitle(title);

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

    private void validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new InvalidArgumentException("Task title cannot be empty.");
        }

        if (title.contains(";")) {
            throw new InvalidArgumentException("Task title cannot contain ';'.");
        }

        if (title.contains("\n") || title.contains("\r")) {
            throw new InvalidArgumentException("Task title cannot contain line breaks.");
        }
    }

    private void validateUpdateOptions(String title, TaskStatus status, TaskPriority priority) {
        boolean hasTitle = title != null && !title.isBlank();
        boolean hasStatus = status != null;
        boolean hasPriority = priority != null;

        if (!hasTitle && !hasStatus && !hasPriority) {
            throw new InvalidArgumentException("Provide at least one field to update: title, status, or priority.");
        }
    }
}
