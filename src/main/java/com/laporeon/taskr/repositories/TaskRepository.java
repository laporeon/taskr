package com.laporeon.taskr.repositories;

import com.laporeon.taskr.entities.Task;
import com.laporeon.taskr.enums.TaskPriority;
import com.laporeon.taskr.enums.TaskStatus;
import com.laporeon.taskr.helpers.FileStorageHandler;

import java.time.Instant;
import java.util.List;

public class TaskRepository {

    public void save(Task task) {
        FileStorageHandler.saveToFile(task);
    }

    public List<Task> listTasksByStatus(TaskStatus status) {
        return FileStorageHandler.readFile()
                                 .stream()
                                 .filter(t -> t.getStatus().equals(status))
                                 .toList();
    }

    public List<Task> listTasks() {
        return FileStorageHandler.readFile();
    }

    public void update(int index, String title, TaskStatus status, TaskPriority priority) {
        List<Task> tasks = FileStorageHandler.readFile();

        Task task = tasks.get(index - 1);

        if (title != null) task.setTitle(title);
        if (status != null) task.setStatus(status);
        if (priority != null) task.setPriority(priority);

        task.setUpdatedAt(Instant.now());

        FileStorageHandler.overwriteFileContent(tasks);
    }

    public void delete(int index) {
        List<Task> tasks = FileStorageHandler.readFile();

        Task task = tasks.get(index - 1);
        tasks.remove(task);

        FileStorageHandler.overwriteFileContent(tasks);
    }

}
