import { TaskInput } from '@interfaces/Task';
import { TaskRepository } from '@repositories/task.repository';

export class TaskService {
  constructor(private readonly taskRepository: TaskRepository) {}

  async create({ title, priority }: TaskInput) {
    await this.taskRepository.add({ title, priority });
  }

  async list(status: string) {
    const tasks = await this.taskRepository.get(status);
    this.taskRepository.render(tasks);
  }
}
