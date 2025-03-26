import { Command } from 'commander';

import { TaskPriority, Colors } from '@enums/index';
import { TaskRepository } from '@repositories/task.repository';
import { TaskService } from '@services/task.service';

const taskService = new TaskService(new TaskRepository());

const priorityAllowedValues = Object.values(TaskPriority);

export const add = new Command('add')
  .argument('<title>', 'Title of the task')
  .option(
    '-p --priority <priority>',
    'Optional task priority. Value must be: high, medium or low.',
    TaskPriority.LOW,
  )
  .action(async (title: string, options) => {
    const { priority } = options;

    if (!priorityAllowedValues.includes(priority as TaskPriority)) {
      return console.error(
        `${Colors.red}Error: Invalid value for priority: "${priority}". Allowed values are: high, medium or low.`,
      );
    }

    await taskService.create({ title, priority });
  })
  .description('Add a new task.');
