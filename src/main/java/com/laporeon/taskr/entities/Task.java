package com.laporeon.taskr.entities;

import com.laporeon.taskr.enums.Color;
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

    public String toCsvString() {
        return String.format(
                "%s;%s;%s;%s;%s;%s",
                id,
                title,
                status.getValue(),
                priority.getValue(),
                createdAt,
                updatedAt
        );
    }

    @Override
    public String toString() {
        String displayTitle = (status == TaskStatus.DONE)
                ? Color.GRAY_STRIKETHROUGH.apply(title)
                : title;

        return String.format(
                "%s %s %s",
                status.getColoredSymbol(),
                displayTitle,
                priority.getColoredSymbol()
        );
    }

}
