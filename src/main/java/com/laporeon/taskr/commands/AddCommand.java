package com.laporeon.taskr.commands;

import com.laporeon.taskr.entities.Task;
import com.laporeon.taskr.enums.TaskPriority;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "add",
        description = "Creates a new task."
)
public class AddCommand implements Runnable {

    @CommandLine.Parameters(index = "0", description = "Task title")
    private String title;

    @CommandLine.Option(names = {"-d", "--description"},
            description = "Optional task description",
            defaultValue = "")
    private String description;

    @CommandLine.Option(names = {"-p", "--priority"},
            description = "Optional task priority (low, medium, high). Default: low")
    private String priority;

    @Override
    public void run() {
        Task task = Task.builder()
                .title(title)
                .description(description)
                .priority(TaskPriority.fromString(priority.toLowerCase()))
                .build();

        System.out.println("Task: " + task.toString());
        System.out.println("\u001B[32m✔\u001B[0m Task successfully created!");
    }
}
