package com.laporeon.taskr.commands;

import com.laporeon.taskr.entities.Task;
import com.laporeon.taskr.helpers.TaskboardRender;
import com.laporeon.taskr.services.TaskService;
import picocli.CommandLine.Command;

import java.util.List;

@Command(
        name = "list",
        description = "List all tasks.",
        mixinStandardHelpOptions = true
)
public class ListCommand implements Runnable {

    private final TaskService taskService;

    public ListCommand(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void run() {
        List<Task> tasks = taskService.listTasks();
        System.out.println(TaskboardRender.renderTaskboard(tasks));
    }
}
