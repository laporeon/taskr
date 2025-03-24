import { Command } from 'commander';

import { TaskRepository } from '@repositories/task.repository';
import { TaskService } from '@services/task.service';

const taskService = new TaskService(new TaskRepository());

export const add = new Command('add')
  .argument('<title>', 'Title of the task')
  .argument('[description]', 'Optional task description')
  .action(async (title: string, description?: string) => {
    await taskService.create({ title, description });
  })
  .description('Add a new task');
