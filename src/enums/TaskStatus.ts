import { Colors } from './Colors';

export enum TaskStatus {
  TODO = 'todo',
  IN_PROGRESS = 'in-progress',
  DONE = 'done',
}

export const TaskStatusSymbols: Record<TaskStatus, string> = {
  [TaskStatus.TODO]: `${Colors.orange}◻${Colors.reset} `,
  [TaskStatus.IN_PROGRESS]: `${Colors.blue}↻${Colors.reset} `,
  [TaskStatus.DONE]: `${Colors.green}✓ ${Colors.gray}${Colors.strikethrough}`,
};
