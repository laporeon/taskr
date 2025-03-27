import { Colors } from './Colors';

export enum TaskPriority {
  LOW = 'low',
  MEDIUM = 'medium',
  HIGH = 'high',
}

export const TaskPrioritySymbols: Record<TaskPriority, string> = {
  [TaskPriority.HIGH]: `${Colors.red}[↑↑↑]${Colors.reset} `,
  [TaskPriority.MEDIUM]: `${Colors.yellow}[↑↑]${Colors.reset} `,
  [TaskPriority.LOW]: `${Colors.green}[↑]${Colors.reset}`,
};
