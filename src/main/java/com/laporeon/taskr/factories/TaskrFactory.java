package com.laporeon.taskr.factories;

import com.laporeon.taskr.Taskr;
import com.laporeon.taskr.commands.AddCommand;
import com.laporeon.taskr.commands.DeleteCommand;
import com.laporeon.taskr.commands.ListCommand;
import com.laporeon.taskr.commands.UpdateCommand;
import com.laporeon.taskr.enums.Color;
import com.laporeon.taskr.repositories.TaskRepository;
import com.laporeon.taskr.services.TaskService;
import picocli.CommandLine;

public class TaskrFactory {

    public static CommandLine create() {
        TaskRepository taskRepository = new TaskRepository();
        TaskService taskService = new TaskService(taskRepository);

        return new CommandLine(new Taskr())
                .addSubcommand("add", new AddCommand(taskService))
                .addSubcommand("delete", new DeleteCommand(taskService))
                .addSubcommand("list", new ListCommand(taskService))
                .addSubcommand("update", new UpdateCommand(taskService))
                .setExecutionExceptionHandler((ex, cmd, parseResult) -> {
                    System.err.printf("%s✘ %s %s%n", Color.RED, ex.getMessage(), Color.RESET);
                    return 1;
                });
    }

}
