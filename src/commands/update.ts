import { Command } from 'commander';

import { Colors } from '@enums/Colors';
import { TaskPriority, TaskStatus } from '@enums/index';
import { TaskRepository } from '@repositories/task.repository';
import { TaskService } from '@services/task.service';
import { Validator } from '@utils/Validator';

const taskService = new TaskService(new TaskRepository());

const validator = new Validator();

export const update = new Command('update')
  .argument('<id>', 'Task id.')
  .option('-t --title <title>', 'Optional new task title. ')
  .option(
    '-s --status <status>',
    'Optional new task status. Value must be: todo, in-progress or done.',
  )
  .option(
    '-p --priority <priority>',
    'Optional task priority. Value must be: high, medium or low.',
  )
  .action(async (id: string, options) => {
    const taskId = parseInt(id);

    // TODO: improve input validations
    if (!options.title && !options.status && !options.priority) {
      return console.error(
        `${Colors.red}Missing one or more updated fields. You can either update title, status, priority or all of them at once.`,
      );
    }

    validator.validate([
      { value: options.status, enum: TaskStatus, fieldName: 'status' },
      { value: options.priority, enum: TaskPriority, fieldName: 'priority' },
    ]);

    await taskService.update(taskId, options);
  })
  .description(
    'Update task title, status or priority. You can also update all at once.',
  );
