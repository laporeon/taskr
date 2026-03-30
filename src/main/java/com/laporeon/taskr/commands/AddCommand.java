package com.laporeon.taskr.commands;

import com.laporeon.taskr.enums.Color;
import com.laporeon.taskr.enums.TaskPriority;
import com.laporeon.taskr.enums.TaskStatus;
import com.laporeon.taskr.repositories.TaskRepository;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "add",
        description = "Creates a new task."
)
public class AddCommand implements Runnable {

    private final TaskRepository taskRepository;

    public AddCommand(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @CommandLine.Parameters(index = "0", description = "Task title")
    private String title;

    @CommandLine.Option(names = {"-s", "--status"},
            description = "Optional task status (todo, in-progress,done). Default: todo",
            defaultValue = "todo")
    private String status;

    @CommandLine.Option(names = {"-p", "--priority"},
            description = "Optional task priority (low, medium, high). Default: low",
            defaultValue = "low")
    private String priority;

    @Override
    public void run() {
        taskRepository.createTask(title, TaskStatus.fromString(status), TaskPriority.fromString(priority));
        System.out.printf("%s✔ Task successfully created!%s", Color.GREEN, Color.RESET);
    }
}
