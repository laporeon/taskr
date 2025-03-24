import { TaskInput } from '@interfaces/Task';
import { TaskRepository } from '@repositories/task.repository';

export class TaskService {
  constructor(private readonly taskRepository: TaskRepository) {}

  async create({ title, description }: TaskInput) {
    await this.taskRepository.add({ title, description });
  }

  async list() {
    const tasks = await this.taskRepository.get();
    console.log({ tasks });
  }
}
