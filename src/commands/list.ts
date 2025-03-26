import { Command } from 'commander';

import { Colors } from '@enums/index';
import { TaskStatus } from '@enums/TaskStatus';
import { TaskRepository } from '@repositories/task.repository';
import { TaskService } from '@services/task.service';

const taskService = new TaskService(new TaskRepository());

const statusAllowedValues = Object.values(TaskStatus);

export const list = new Command('list')
  .option(
    '-s --status <status>',
    'Optional new task status. Value must be: todo, in-progress or done.',
  )
  .action(async options => {
    const { status } = options;

    if (status && !statusAllowedValues.includes(status as TaskStatus)) {
      return console.error(
        `${Colors.red}Error: Invalid value for priority: "${status}". Allowed values are: todo, in-progress or done.`,
      );
    }
    await taskService.list(status);
  })
  .description('List all tasks or list tasks by status.');
