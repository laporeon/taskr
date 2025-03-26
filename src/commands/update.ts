import { Command } from 'commander';

import { Colors } from '@enums/Colors';
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
    const { title, status, priority } = options;

    // TODO: improve input validations
    if (!title && !status && !priority) {
      return console.error(
        `${Colors.red}Missing one or more updated fields. You can either update title, status, priority or all of them at once.`,
      );
    }

    validator.validate(status, priority);

    await taskService.update({ id: parseInt(id), title, status, priority });
  })
  .description('Update task title, status or priority.');
