package com.laporeon.taskr.commands;

import com.laporeon.taskr.entities.Task;
import com.laporeon.taskr.helpers.TaskboardRender;
import com.laporeon.taskr.repositories.TaskRepository;
import picocli.CommandLine.Command;

import java.util.List;

@Command(
        name = "list",
        description = "List all tasks."
)
public class ListCommand implements Runnable {

    private final TaskRepository taskRepository;

    public ListCommand(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void run() {
        List<Task> tasks = taskRepository.listTasks();
        System.out.println(TaskboardRender.renderTaskboard(tasks));
    }
}
