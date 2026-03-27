package com.laporeon.taskr;


import picocli.CommandLine;

@CommandLine.Command(
		name = "taskr",
		description = "Says hello to user"
)
public class Taskr implements Runnable{

	public static void main(String[] args) {
		int exitCode = new CommandLine(new Taskr()).execute(args);
		System.exit(exitCode);
	}

	@Override
	public void run() {
		System.out.println("Hello World!");
	}
}
