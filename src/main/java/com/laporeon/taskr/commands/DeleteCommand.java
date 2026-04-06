package com.laporeon.taskr.commands;

import com.laporeon.taskr.enums.Color;
import com.laporeon.taskr.repositories.TaskRepository;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "delete",
        description = "Delete a task by its index (1-based)"
)
public class DeleteCommand implements Runnable {

    private final TaskRepository taskRepository;

    @CommandLine.Option(names = {"-i", "--index"},
            description = "Task index",
            required = true)
    private int index;

    public DeleteCommand(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void run() {
        taskRepository.deleteTask(index);
        System.out.printf("%s✔ Task successfully deleted!%s", Color.GREEN, Color.RESET);
    }
}
