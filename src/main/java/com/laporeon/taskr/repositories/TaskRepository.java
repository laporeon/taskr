package com.laporeon.taskr.repositories;

import com.laporeon.taskr.entities.Task;
import com.laporeon.taskr.enums.TaskPriority;
import com.laporeon.taskr.enums.TaskStatus;
import com.laporeon.taskr.helpers.FileStorageHandler;

import java.util.List;

public class TaskRepository {

    public void createTask(String title, TaskStatus status, TaskPriority priority) {
        Task task = Task.builder()
                .id(getNextId())
                .title(title)
                .status(status)
                .priority(priority)
                .build();

        FileStorageHandler.saveToFile(task);
    }

    public List<Task> listTasks() {
        return FileStorageHandler.readFile();
    }

    private Integer getNextId() {
        return listTasks().size() + 1;
    }

}
