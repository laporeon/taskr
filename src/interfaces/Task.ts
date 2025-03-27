import { TaskPriority, TaskStatus } from '@enums/index';

interface Task {
  id: number;
  title: string;
  priority: TaskPriority;
  status: TaskStatus;
  createdAt: string;
  updatedAt?: string;
}

type CreateTaskInput = Pick<Task, 'title' | 'priority'>;

export { Task, CreateTaskInput };
