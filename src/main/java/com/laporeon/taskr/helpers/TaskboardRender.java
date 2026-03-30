package com.laporeon.taskr.helpers;

import com.laporeon.taskr.entities.Task;
import com.laporeon.taskr.enums.Color;
import com.laporeon.taskr.enums.TaskStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaskboardRender {

    public static String renderTaskboard(List<Task> tasks) {
        Map<TaskStatus, Long> countByStatus = tasks.stream()
                                                   .collect(Collectors.groupingBy(Task::getStatus, Collectors.counting()));

        long todo = countByStatus.getOrDefault(TaskStatus.TODO, 0L);
        long inProgress = countByStatus.getOrDefault(TaskStatus.IN_PROGRESS, 0L);
        long done = countByStatus.getOrDefault(TaskStatus.DONE, 0L);

        StringBuilder sb = new StringBuilder();

        sb.append("\n")
          .append(Color.BOLD_UNDERLINE)
          .append("Tasks")
          .append(Color.RESET)
          .append("\n\n");

        tasks.stream()
             .map(Task::toString)
             .forEach(task -> sb.append("  ").append(task).append("\n"));

        sb.append(renderSummary(todo, inProgress, done));

        return sb.toString();
    }

    private static String renderSummary(long todo, long inProgress, long done) {
        return String.format(
                "\n%s%d %stodo · %s%d %sin-progress · %s%d %sdone%s\n",
                Color.ORANGE, todo, Color.RESET,
                Color.BLUE, inProgress, Color.RESET,
                 Color.GREEN, done, Color.RESET,
                Color.RESET
        );
    }
}
