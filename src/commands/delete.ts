import { Command } from 'commander';

import { TaskRepository } from '@repositories/task.repository';
import { TaskService } from '@services/task.service';

const taskService = new TaskService(new TaskRepository());

export const remove = new Command('remove')
  .argument('<id>', 'Task id.')
  .action(async (id: string) => {
    const taskId = parseInt(id);

    await taskService.delete(taskId);
  })
  .description('Delete a task.');
