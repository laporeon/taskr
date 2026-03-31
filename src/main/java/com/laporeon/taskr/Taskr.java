package com.laporeon.taskr;

import com.laporeon.taskr.commands.AddCommand;
import com.laporeon.taskr.commands.DeleteCommand;
import com.laporeon.taskr.commands.ListCommand;
import com.laporeon.taskr.enums.Color;
import com.laporeon.taskr.repositories.TaskRepository;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
		name = "taskr",
		description = "Java-based CLI to manage tasks"
)
public class Taskr implements Runnable {

	public static void main(String[] args) {
		TaskRepository taskRepository = new TaskRepository();

		CommandLine commandLine = new CommandLine(new Taskr())
				.addSubcommand("add", new AddCommand(taskRepository))
				.addSubcommand("list", new ListCommand(taskRepository))
				.addSubcommand("delete", new DeleteCommand(taskRepository))
				.setExecutionExceptionHandler((ex, cmd, parseResult) -> {
					System.err.printf("%s✘ %s %s", Color.RED, ex.getMessage(), Color.RESET);
					return 1;
				});

		int exitCode = commandLine.execute(args);
		System.exit(exitCode);
	}

	@Override
	public void run() {
		new CommandLine(this).usage(System.out);
	}
}
