package com.laporeon.taskr.entities;

import com.laporeon.taskr.enums.TaskPriority;
import com.laporeon.taskr.enums.TaskStatus;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public class Task {

    @Builder.Default
    private UUID id = UUID.randomUUID();

    private String title;

    private String description;

    @Builder.Default
    private TaskStatus status = TaskStatus.TODO;

    @Builder.Default
    private TaskPriority priority = TaskPriority.LOW;

    @Builder.Default
    private Instant createdAt = Instant.now();

    private Instant updatedAt;

    public String toCsvString() {
        return String.format("\n%s;%s,%s,%s;%s;%s;%s", id, title, description, status, priority, createdAt, updatedAt);
    }

    public String toString() {
        return """
               %s. %s %s %s
               """.formatted(id, title, status.getSymbol(), priority.getSymbol());
    }

}
