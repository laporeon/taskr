package com.laporeon.taskr.helpers;

import com.laporeon.taskr.entities.Task;
import com.laporeon.taskr.enums.Color;
import com.laporeon.taskr.enums.TaskPriority;
import com.laporeon.taskr.enums.TaskStatus;

import java.time.Instant;
import java.util.UUID;

public class TaskSerializer {

    public static Task fromFields(String[] attributes) {
        Task task = new Task();
        task.setId(UUID.fromString(attributes[0]));
        task.setTitle(attributes[1]);
        task.setStatus(TaskStatus.fromString(attributes[2]));
        task.setPriority(TaskPriority.fromString(attributes[3]));
        task.setCreatedAt(Instant.parse(attributes[4]));
        task.setUpdatedAt(Instant.parse(attributes[5]));
        return task;
    }

    public static Task toEntity(String title, TaskStatus status, TaskPriority priority) {
        Task task = new Task();
        task.setId(UUID.randomUUID());
        task.setTitle(title);
        task.setStatus(status);
        task.setPriority(priority);
        task.setCreatedAt(Instant.now());
        task.setUpdatedAt(Instant.now());
        return task;
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
