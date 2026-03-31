package com.laporeon.taskr.commands;

import com.laporeon.taskr.enums.Color;
import com.laporeon.taskr.repositories.TaskRepository;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "delete",
        description = "Delete a task by its unique id."
)
public class DeleteCommand implements Runnable {

    private final TaskRepository taskRepository;

    @CommandLine.Option(names = {"-i", "--id"},
            description = "Task id",
            required = true)
    private int id;

    public DeleteCommand(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void run() {
        taskRepository.deleteTask(id);
        System.out.printf("%s✔ Task successfully deleted!%s", Color.GREEN, Color.RESET);
    }
}
