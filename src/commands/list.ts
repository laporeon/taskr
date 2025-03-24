import { Command } from 'commander';

import { TaskService } from '@services/task.service';

const taskService = new TaskService();

export const list = new Command('list')
  // TODO: add functionality to list by status provided by user input
  .action(async () => {
    await taskService.list();
  })
  .description('List all tasks');
