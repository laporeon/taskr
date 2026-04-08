package com.laporeon.taskr.commands;

import com.laporeon.taskr.enums.Color;
import com.laporeon.taskr.enums.TaskPriority;
import com.laporeon.taskr.enums.TaskStatus;
import com.laporeon.taskr.services.TaskService;
import picocli.CommandLine;
import picocli.CommandLine.Command;


@Command(
        name = "update",
        description = "Update a task by its index (1-based): title, status, and/or priority"
)
public class UpdateCommand implements Runnable {

    private final TaskService taskService;

    public UpdateCommand(TaskService taskService) {
        this.taskService = taskService;
    }

    @CommandLine.Option(names = {"-i", "--index"},
            description = "Task index",
            required = true)
    private int index;

    @CommandLine.Option(names = {"-t", "--title"},
            description = "New task title")
    private String title;

    @CommandLine.Option(names = {"-s", "--status"},
            description = "Optional new task status.")
    private String status;

    @CommandLine.Option(names = {"-p", "--priority"},
            description = "Optional new task priority (low, medium, high)")
    private String priority;

    @Override
    public void run() {
        taskService.updateTask(index, title, TaskStatus.fromString(status), TaskPriority.fromString(priority));
        System.out.printf("%s✔ Task successfully updated!%s", Color.GREEN, Color.RESET);
    }

}
