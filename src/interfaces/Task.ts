import { TaskStatus } from '@utils/taskStatus';

interface Task {
  id: string;
  title: string;
  description?: string;
  status: TaskStatus;
  createdAt: string;
  updatedAt?: string;
}

type TaskInput = Pick<Task, 'title' | 'description'>;

export { Task, TaskInput };
