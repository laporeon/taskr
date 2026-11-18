package com.laporeon.taskr.helpers;

import com.laporeon.taskr.entities.Task;
import com.laporeon.taskr.enums.Color;
import com.laporeon.taskr.enums.TaskPriority;
import com.laporeon.taskr.enums.TaskStatus;

import java.time.Instant;
import java.util.UUID;

public class TaskSerializer {
    public static Task fromFields(String[] attributes) {
        return Task.restore(
                UUID.fromString(attributes[0]),
                attributes[1],
                TaskStatus.fromString(attributes[2]),
                TaskPriority.fromString(attributes[3]),
                Instant.parse(attributes[4]),
                Instant.parse(attributes[5])
        );
    }

    public static Task toEntity(String title, TaskStatus status, TaskPriority priority) {
        return Task.create(
                title,
                status,
                priority
        );
    }

    public static String toCsvString(Task task) {
        return String.format(
                "%s;%s;%s;%s;%s;%s",
                task.getId(),
                task.getTitle(),
                task.getStatus().getValue(),
                task.getPriority().getValue(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }

    public static String toDisplayString(Task task, int index) {
        String formattedIndex = (task.getStatus() == TaskStatus.DONE)
                ? Color.GRAY.apply(index + ".")
                : index + ".";

        String formattedTitle = (task.getStatus() == TaskStatus.DONE)
                ? Color.GRAY_STRIKETHROUGH.apply(task.getTitle())
                : task.getTitle();

        return String.format(
                "%s %s %s %s",
                formattedIndex,
                task.getStatus().getColoredSymbol(),
                formattedTitle,
                task.getPriority().getColoredSymbol()
        );
    }
}
