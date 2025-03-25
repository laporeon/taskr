import { Colors } from './Colors';

export enum TaskPriority {
  HIGH = 'high',
  MEDIUM = 'medium',
  LOW = 'low',
}

export const TaskPrioritySymbols: Record<TaskPriority, string> = {
  [TaskPriority.HIGH]: `${Colors.red}[↑↑↑]${Colors.reset} `,
  [TaskPriority.MEDIUM]: `${Colors.yellow}[↑↑]${Colors.reset} `,
  [TaskPriority.LOW]: `${Colors.green}[↑]${Colors.reset}`,
};
