import { Command } from 'commander';

import { TaskRepository } from '@repositories/task.repository';
import { TaskService } from '@services/task.service';

const taskService = new TaskService(new TaskRepository());

export const list = new Command('list')
  .argument('[status]', 'Optional task status.')
  .action(async (status: string) => {
    await taskService.list(status);
  })
  .description('List all tasks or list tasks by status.');
