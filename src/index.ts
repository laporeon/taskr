#!/usr/bin/env node

import { program } from 'commander';

import { commands } from '@commands/index';
import { logo } from '@utils/logo';

logo();

commands.forEach(command => program.addCommand(command));

program
  .name('taskr')
  .description('A task tracker CLI.')
  .version('1.0.0')
  .addHelpText(
    'after',
    `\nExamples:
  $ taskr add "Clean node_modules"
  $ taskr add "Study" "Do 5 leetcode exercises"
  $ taskr list"`,
  )
  .showSuggestionAfterError();

program.parse();
