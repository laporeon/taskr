package com.laporeon.taskr.commands;

import com.laporeon.taskr.entities.Task;
import com.laporeon.taskr.enums.TaskStatus;
import com.laporeon.taskr.helpers.TaskboardRender;
import com.laporeon.taskr.services.TaskService;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.List;

@Command(
        name = "list",
        description = "List all tasks or fetch tasks by status.",
        mixinStandardHelpOptions = true
)
public class ListCommand implements Runnable {

    private final TaskService taskService;

    public ListCommand(TaskService taskService) {
        this.taskService = taskService;
    }

    @CommandLine.Option(
            names = {"-s", "--status"},
            paramLabel = "STATE",
            description = "Optional task status (todo, in-progress, done)"
    )
    private String status;

    @Override
    public void run() {
        List<Task> tasks = taskService.listTasks(TaskStatus.fromString(status));
        System.out.println(TaskboardRender.renderTaskboard(tasks));
    }
}
