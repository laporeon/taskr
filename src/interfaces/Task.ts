import { TaskStatus } from '@utils/taskStatus';

interface Task {
  id: string;
  title: string;
  description?: string;
  status: TaskStatus;
  createdAt: string;
  updatedAt?: string;
}

// TODO: refactor here
interface TaskInput {
  title: string;
  description?: string;
}

export { Task, TaskInput };
