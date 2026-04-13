package com.laporeon.taskr.commands;

import com.laporeon.taskr.enums.Color;
import com.laporeon.taskr.enums.TaskPriority;
import com.laporeon.taskr.enums.TaskStatus;
import com.laporeon.taskr.services.TaskService;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "add",
        description = "Creates a new task.",
        mixinStandardHelpOptions = true
)
public class AddCommand implements Runnable {

    private final TaskService taskService;

    public AddCommand(TaskService taskService) {
        this.taskService = taskService;
    }

    @CommandLine.Parameters(index = "0", paramLabel = "TITLE", description = "Task title")
    private String title;

    @CommandLine.Option(
            names = {"-s", "--status"},
            paramLabel = "STATE",
            description = "Optional task status (todo, in-progress, done). Default: ${DEFAULT-VALUE}",
            defaultValue = "todo",
            showDefaultValue = CommandLine.Help.Visibility.NEVER
    )
    private String status;

    @CommandLine.Option(names = {"-p", "--priority"},
            paramLabel = "LEVEL",
            description = "Optional task priority (low, medium, high). Default: ${DEFAULT-VALUE}",
            defaultValue = "low",
            showDefaultValue = CommandLine.Help.Visibility.NEVER
    )
    private String priority;

    @Override
    public void run() {
        taskService.createTask(title, TaskStatus.fromString(status), TaskPriority.fromString(priority));
        System.out.printf("%s✔ Task successfully created!%s", Color.GREEN, Color.RESET);
    }
}
