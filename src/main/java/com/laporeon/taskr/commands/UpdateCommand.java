package com.laporeon.taskr.commands;

import com.laporeon.taskr.enums.Color;
import com.laporeon.taskr.enums.TaskPriority;
import com.laporeon.taskr.enums.TaskStatus;
import com.laporeon.taskr.repositories.TaskRepository;
import picocli.CommandLine;
import picocli.CommandLine.Command;


@Command(
        name = "update",
        description = "Update task status or title by its unique id"
)
public class UpdateCommand implements Runnable {

    private final TaskRepository taskRepository;

    public UpdateCommand(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @CommandLine.Option(names = {"-i", "--id"},
            description = "Task id",
            required = true)
    private int id;

    @CommandLine.Option(names = {"-t", "--title"},
            description = "New task title",
            required = true)
    private String title;

    @CommandLine.Option(names = {"-s", "--status"},
            description = "Optional new task status.")
    private String status;

    @CommandLine.Option(names = {"-p", "--priority"},
            description = "Optional new task priority (low, medium, high)")
    private String priority;

    @Override
    public void run() {
        taskRepository.updateTask(id, title, TaskStatus.fromString(status), TaskPriority.fromString(priority));
        System.out.printf("%s✔ Task successfully updated!%s", Color.GREEN, Color.RESET);
    }

}
