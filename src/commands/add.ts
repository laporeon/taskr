import { Command } from 'commander';

import { TaskPriority } from '@enums/index';
import { TaskRepository } from '@repositories/task.repository';
import { TaskService } from '@services/task.service';
import { Validator } from '@utils/Validator';

const validator = new Validator();

const taskService = new TaskService(new TaskRepository());

export const add = new Command('add')
  .argument('<title>', 'Title of the task')
  .option(
    '-p --priority <priority>',
    'Optional task priority. Value must be: high, medium or low.',
    TaskPriority.LOW,
  )
  .action(async (title: string, options) => {
    const { priority } = options;

    validator.validate([
      { value: priority, enum: TaskPriority, fieldName: 'priority' },
    ]);

    await taskService.create({ title, priority });
  })
  .description('Add a new task.');
