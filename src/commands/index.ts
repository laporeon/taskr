import { Command } from 'commander';

import { add } from './add';
import { remove } from './delete';
import { list } from './list';

export const commands: Command[] = [add, list, remove];
