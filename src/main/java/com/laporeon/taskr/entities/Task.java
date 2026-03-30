package com.laporeon.taskr.entities;

import com.laporeon.taskr.enums.Color;
import com.laporeon.taskr.enums.TaskPriority;
import com.laporeon.taskr.enums.TaskStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class Task {

    private int id;

    private String title;

    @Builder.Default
    private TaskStatus status = TaskStatus.TODO;

    @Builder.Default
    private TaskPriority priority = TaskPriority.LOW;

    @Builder.Default
    private Instant createdAt = Instant.now();

    private Instant updatedAt;

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
        String formattedId = String.format("%s.", id);

        String displayId = (status == TaskStatus.DONE)
                ? Color.GRAY.apply(formattedId)
                : formattedId;

        String displayTitle = (status == TaskStatus.DONE)
                ? Color.GRAY_STRIKETHROUGH.apply(title)
                : title;

        return String.format(
                "%s %s %s %s",
                displayId,
                status.getColoredSymbol(),
                displayTitle,
                priority.getColoredSymbol()
        );
    }

}
