import { TaskInput } from '@interfaces/Task';
import { TaskRepository } from '@repositories/task.repository';

export class TaskService {
  constructor(private readonly taskRepository: TaskRepository) {}

  async create({ title, description }: TaskInput) {
    await this.taskRepository.add({ title, description });
  }

  async list(status: string) {
    const tasks = await this.taskRepository.get(status);
    console.log({ tasks });
  }
}
