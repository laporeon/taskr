import { Command } from 'commander';

import { add } from './add';
import { list } from './list';

export const commands: Command[] = [add, list];
