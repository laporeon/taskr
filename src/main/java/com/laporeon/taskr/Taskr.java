package com.laporeon.taskr;

import com.laporeon.taskr.factories.TaskrFactory;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
		name = "taskr",
		description = "Java-based CLI to manage tasks"
)
public class Taskr implements Runnable {

	public static void main(String[] args) {
		int exitCode = TaskrFactory.create().execute(args);
		System.exit(exitCode);
	}

	@Override
	public void run() {
		new CommandLine(this).usage(System.out);
	}
}
