#!/usr/bin/env node

import { program } from 'commander';

import { add, list } from '@commands/index';
import { logo } from '@utils/logo';

logo();

program.addCommand(add);
program.addCommand(list);

program
  .name('pomotimer')
  .description('A Pomodoro CLI timer.')
  .version('1.0.0')
  .addHelpText(
    'after',
    `\nExamples:
  $ taskr -a "Clean node_modules"
  $ taskr -a "Study" "Do 5 leetcode exercises"`,
  )
  .showSuggestionAfterError();

program.parse();
