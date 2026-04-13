package com.laporeon.taskr.helpers;

import com.laporeon.taskr.entities.Task;
import com.laporeon.taskr.enums.Color;
import com.laporeon.taskr.enums.TaskStatus;

public class TaskSerializer {

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
