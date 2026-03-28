package com.laporeon.taskr.repositories;

import com.laporeon.taskr.entities.Task;
import com.laporeon.taskr.enums.TaskPriority;
import com.laporeon.taskr.helpers.FileStorageHandler;

public class TaskRepository {

    public void createTask(String title, String description, TaskPriority priority) {
        Task task = Task.builder()
                .title(title)
                .description(description)
                .priority(priority)
                .build();

        FileStorageHandler.saveTaskToFile(task);
    }

}
