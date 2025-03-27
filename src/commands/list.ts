import { Command } from 'commander';

import { TaskStatus } from '@enums/TaskStatus';
import { TaskRepository } from '@repositories/task.repository';
import { TaskService } from '@services/task.service';
import { Validator } from '@utils/Validator';

const taskService = new TaskService(new TaskRepository());

const validator = new Validator();

export const list = new Command('list')
  .option(
    '-s --status <status>',
    'Optional new task status. Value must be: todo, in-progress or done.',
  )
  .action(async options => {
    const { status } = options;

    validator.validate([
      { value: status, enum: TaskStatus, fieldName: 'status' },
    ]);

    await taskService.list(status);
  })
  .description('List all tasks or list tasks by status.');
