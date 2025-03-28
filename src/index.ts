#!/usr/bin/env node

import { program } from 'commander';

import { commands } from '@commands/index';

commands.forEach(command => program.addCommand(command));

program
  .name('taskr')
  .description('A task tracker CLI.')
  .version('1.0.0')
  .addHelpText(
    'after',
    `\nExamples:
  $ taskr add "Clean node_modules" 
  $ taskr add "Study" -p high
  $ taskr list
  $ taskr list -s "todo"
  $ taskr update 5 -t "New Task Title"
  $ taskr update 5 -p "high"
  $ taskr update 5 -s "in-progress"
  $ taskr update 5 -t "New Task Title -p "medium" -s "done"
  $ taskr remove 2
  `,
  )
  .showSuggestionAfterError();

program.parse();
