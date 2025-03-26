import { Command } from 'commander';

import { TaskPriority, Colors, TaskStatus } from '@enums/index';
import { TaskRepository } from '@repositories/task.repository';
import { TaskService } from '@services/task.service';

const taskService = new TaskService(new TaskRepository());

const priorityAllowedValues = Object.values(TaskPriority);
const statusAllowedValues = Object.values(TaskStatus);

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
    const { title, status, priority } = options;

    // TODO: add validation to check if user sent at least one option

    if (status && !statusAllowedValues.includes(status as TaskStatus)) {
      return console.error(
        `${Colors.red}Error: Invalid value for priority: "${status}". Allowed values are: todo, in-progress or done.`,
      );
    }
    if (priority && !priorityAllowedValues.includes(priority as TaskPriority)) {
      return console.error(
        `${Colors.red}Error: Invalid value for priority: "${priority}". Allowed values are: high, medium or low.`,
      );
    }

    console.log({ id, title, status, priority });

    await taskService.update({ id: parseInt(id), title, status, priority });
  })
  .description('Update task title, status or priority.');
