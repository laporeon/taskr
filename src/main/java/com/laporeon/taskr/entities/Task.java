package com.laporeon.taskr.entities;

import com.laporeon.taskr.enums.TaskPriority;
import com.laporeon.taskr.enums.TaskStatus;

import java.time.Instant;
import java.util.UUID;

public class Task {
    private final UUID id;
    private String title;
    private TaskStatus status;
    private TaskPriority priority;
    private final Instant createdAt;
    private Instant updatedAt;

    private Task(UUID id, String title, TaskStatus status, TaskPriority priority, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.priority = priority;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Task create(String title, TaskStatus status, TaskPriority priority) {
        Instant now = Instant.now();
        return new Task(UUID.randomUUID(), title, status, priority, now, now);
    }

    public static Task restore(UUID id, String title, TaskStatus status, TaskPriority priority,
                               Instant createdAt, Instant updatedAt) {
        return new Task(id, title, status, priority, createdAt, updatedAt);
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
