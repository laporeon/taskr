import { Command } from 'commander';

import { add } from './add';
import { remove } from './delete';
import { list } from './list';
import { update } from './update';

export const commands: Command[] = [add, list, update, remove];
