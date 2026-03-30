package com.laporeon.taskr.repositories;

import com.laporeon.taskr.entities.Task;
import com.laporeon.taskr.enums.TaskPriority;
import com.laporeon.taskr.enums.TaskStatus;
import com.laporeon.taskr.helpers.FileStorageHandler;

import java.util.List;

public class TaskRepository {

    public void createTask(String title, TaskStatus status, TaskPriority priority) {
        int lastIndex = getLastIndex();

        Task task = Task.builder()
                .id(lastIndex + 1)
                .title(title)
                .status(status)
                .priority(priority)
                .build();

        FileStorageHandler.saveToFile(task);
    }

    public List<Task> listTasks() {
        return FileStorageHandler.readFile();
    }

    private Integer getLastIndex() {
        return listTasks().size();
    }

}
