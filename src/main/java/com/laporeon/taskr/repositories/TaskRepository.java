package com.laporeon.taskr.repositories;

import com.laporeon.taskr.entities.Task;
import com.laporeon.taskr.enums.TaskPriority;
import com.laporeon.taskr.enums.TaskStatus;
import com.laporeon.taskr.helpers.FileStorageHandler;

import java.util.List;

public class TaskRepository {

    public void save(Task task) {
        FileStorageHandler.saveToFile(task);
    }

    public List<Task> listTasks() {
        return FileStorageHandler.readFile();
    }

    public void update(int index, String title, TaskStatus status, TaskPriority priority) {
        List<Task> tasks = FileStorageHandler.readFile();

        Task task = getTaskByIndex(tasks, index);
        task.update(title, status, priority);

        FileStorageHandler.overwriteFileContent(tasks);
    }

    public void delete(int index) {
        List<Task> tasks = FileStorageHandler.readFile();

        Task task = getTaskByIndex(tasks, index);
        tasks.remove(task);

        FileStorageHandler.overwriteFileContent(tasks);
    }

    private Task getTaskByIndex(List<Task> tasks, int index) {
        return tasks.get(index - 1);
    }
}
