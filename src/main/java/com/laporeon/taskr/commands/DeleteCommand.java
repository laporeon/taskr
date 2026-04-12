package com.laporeon.taskr.commands;

import com.laporeon.taskr.enums.Color;
import com.laporeon.taskr.services.TaskService;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "delete",
        description = "Delete a task by its index (1-based)",
        mixinStandardHelpOptions = true
)
public class DeleteCommand implements Runnable {

    private final TaskService taskService;

    public DeleteCommand(TaskService taskService) {
        this.taskService = taskService;
    }

    @CommandLine.Parameters(
            index = "0",
            paramLabel = "INDEX",
            description = "Task index (1-based)")
    private int index;

    @Override
    public void run() {
        taskService.deleteTask(index);
        System.out.printf("%s✔ Task successfully deleted!%s", Color.GREEN, Color.RESET);
    }
}
