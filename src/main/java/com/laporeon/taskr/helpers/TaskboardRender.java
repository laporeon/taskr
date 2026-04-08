package com.laporeon.taskr.helpers;

import com.laporeon.taskr.entities.Task;
import com.laporeon.taskr.enums.Color;
import com.laporeon.taskr.enums.TaskStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskboardRender {

    public static String renderTaskboard(List<Task> tasks) {
        Map<TaskStatus, Long> countByStatus = tasks.stream()
                                                   .collect(Collectors.groupingBy(Task::getStatus, Collectors.counting()));

        long todo = countByStatus.getOrDefault(TaskStatus.TODO, 0L);
        long inProgress = countByStatus.getOrDefault(TaskStatus.IN_PROGRESS, 0L);
        long done = countByStatus.getOrDefault(TaskStatus.DONE, 0L);

        return renderHeader() + renderBody(tasks) + renderSummary(todo, inProgress, done);
    }

    private static String renderHeader() {
        return "\n" + Color.BOLD_UNDERLINE + "Tasks" + Color.RESET + "\n\n";
    }

    private static String renderBody(List<Task> tasks) {
        return IntStream.range(0, tasks.size())
                        .mapToObj(index -> renderTaskLine(index + 1, tasks.get(index)))
                        .collect(Collectors.joining());
    }

    private static String renderTaskLine(int index, Task task) {
        String serializedTask = TaskSerializer.toDisplayString(task, index);
        return "  " + serializedTask + "\n";
    }


    private static String renderSummary(long todo, long inProgress, long done) {
        return "\n" + Color.ORANGE + todo + Color.RESET + " todo" + " · " +
                Color.BLUE + inProgress + Color.RESET + " in-progress" + " · " +
                Color.GREEN + done + Color.RESET + " done\n";

    }

}
