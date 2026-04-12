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

    private static final int HELP_WIDTH = 160;

    public static CommandLine create() {

        TaskRepository taskRepository = new TaskRepository();
        TaskService taskService = new TaskService(taskRepository);

        CommandLine cmd = new CommandLine(new Taskr())
                .setUsageHelpWidth(HELP_WIDTH)
                .addSubcommand("add", new AddCommand(taskService))
                .addSubcommand("delete", new DeleteCommand(taskService))
                .addSubcommand("list", new ListCommand(taskService))
                .addSubcommand("update", new UpdateCommand(taskService))
                .setParameterExceptionHandler((ex, args) -> {
                    String helpCommand = ex.getCommandLine().getCommandSpec().qualifiedName() + " --help";
                    System.err.printf("%s✘ %s%s%n", Color.RED, ex.getMessage(), Color.RESET);
                    System.err.printf("Use '%s' to see usage", helpCommand);
                    return ex.getCommandLine().getCommandSpec().exitCodeOnInvalidInput();
                })
                .setExecutionExceptionHandler((ex, c, parseResult) -> {
                    System.err.printf("%s✘ %s %s%n", Color.RED, ex.getMessage(), Color.RESET);
                    return c.getCommandSpec().exitCodeOnExecutionException();
                });

        // Ensure consistent help wrapping for subcommands: root help width is not always inherited.
        cmd.getSubcommands().values().forEach(sub -> sub.setUsageHelpWidth(HELP_WIDTH));

        return cmd;
    }

}
