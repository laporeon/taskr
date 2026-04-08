package com.laporeon.taskr.entities;

import com.laporeon.taskr.enums.TaskPriority;
import com.laporeon.taskr.enums.TaskStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Builder
@Getter
public class Task {

    private UUID id;
    private String title;
    private TaskStatus status;
    private TaskPriority priority;
    private Instant createdAt;
    private Instant updatedAt;

    public void update(String title, TaskStatus status, TaskPriority priority) {
        if (title != null) this.title = title;
        if (status != null) this.status = status;
        if (priority != null) this.priority = priority;

        this.updatedAt = Instant.now();
    }

}
