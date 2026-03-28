package com.laporeon.taskr.commands;

import com.laporeon.taskr.enums.Color;
import com.laporeon.taskr.enums.TaskPriority;
import com.laporeon.taskr.repositories.TaskRepository;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "add",
        description = "Creates a new task."
)
public class AddCommand implements Runnable {

    private static final TaskRepository taskRepository = new TaskRepository();

    @CommandLine.Parameters(index = "0", description = "Task title")
    private String title;

    @CommandLine.Option(names = {"-d", "--description"},
            description = "Optional task description")
    private String description;

    @CommandLine.Option(names = {"-p", "--priority"},
            description = "Optional task priority (low, medium, high). Default: low")
    private String priority;

    @Override
    public void run() {
        taskRepository.createTask(title, description, TaskPriority.fromString(priority.toLowerCase()));
        System.out.printf("%s✔ Task successfully created!%s", Color.GREEN, Color.RESET);
    }
}
