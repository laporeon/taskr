package com.laporeon.taskr.commands;

import com.laporeon.taskr.enums.Color;
import com.laporeon.taskr.services.TaskService;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "delete",
        description = "Delete a task by its index (1-based)"
)
public class DeleteCommand implements Runnable {

    private final TaskService taskService;

    public DeleteCommand(TaskService taskService) {
        this.taskService = taskService;
    }

    @CommandLine.Option(names = {"-i", "--index"},
            description = "Task index",
            required = true)
    private int index;

    @Override
    public void run() {
        taskService.deleteTask(index);
        System.out.printf("%s✔ Task successfully deleted!%s", Color.GREEN, Color.RESET);
    }
}
